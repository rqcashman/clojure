(ns rf-tennis-manager.handler
  (:require [buddy.auth.accessrules :refer [wrap-access-rules restrict]]
            [buddy.auth.backends :as backends]
            [buddy.auth.middleware :refer [wrap-authentication wrap-authorization]]
            [compojure.core :refer :all]
            [compojure.route :as route]
            [ring.middleware.content-type :refer [wrap-content-type]]
            [ring.middleware.defaults :refer [wrap-defaults site-defaults]]
            [ring.middleware.json :as ring-json]
            [ring.middleware.params :as parms]
            [ring.middleware.reload :refer [wrap-reload]]
            [ring.middleware.session :refer [wrap-session]]
            [ring.util.response :as rr]
            [ring.middleware.reload :refer [wrap-reload]]
            [rf-tennis-manager.content.admin :as admin]
            [rf-tennis-manager.content.login :as login]
            [rf-tennis-manager.content.matches :as match]
            [rf-tennis-manager.content.page-layout :as layout]
            [rf-tennis-manager.content.player-availability :as avail]
            [rf-tennis-manager.content.roster :as rost]
            [rf-tennis-manager.content.schedule :as schedule]
            [rf-tennis-manager.content.tabs :as tabs]
            [rf-tennis-manager.data.auth-handler :as auth_data]
            [rf-tennis-manager.data.club-data-handler :as club]
            [rf-tennis-manager.data.player-data-handler :as player]
            [rf-tennis-manager.data.schedule-data-handler :as sched]
            [rf-tennis-manager.data.season-data-handler :as season]
            [rf-tennis-manager.data.team-data-handler :as team]
            [rf-tennis-manager.processors.auth-processor :as auth]
            [rf-tennis-manager.processors.send-email-processor :as em]
            [rf-tennis-manager.processors.service-processor :as pr]))

;(defroutes routes
;           (GET "/" [] (rr/resource-response "index.html" {:root "public"}))
;           (route/resources "/")
;           (route/not-found "Page not found"))
(defn testx
  "docstring"
  []
  (println "resource response: " (rr/resource-response "index.html" {:root "public"}))
  (println (:body (rr/resource-response "index.html" {:root "public"})))
  (println (slurp (:body (rr/resource-response "index.html" {:root "public"}))))
  (rr/resource-response "index.html" {:root "public"}))

(def USE-REACT true)
(def NO-REACT false)

(defroutes app-routes
           ;HTML pages
           (GET "/availability-reply*" {params :params} (layout/application "Availability Response" "" NO-REACT (avail/update_availability (:player-token params) (:available params))))
           (GET "/chgpassword" {session :session params :params} (layout/application "Change Password" "" NO-REACT (login/login login/CHANGE_PASSWORD_PAGE session params)))
           (GET "/login*" {session :session params :params} (layout/application "User Login" "" NO-REACT (login/login login/LOGIN_PAGE session params)))
           (GET "/logout" {session :session params :params} (layout/application "User Login" "" NO-REACT (login/login login/LOGIN_PAGE session params)))
           (GET "/mgr" {session :session request :params} (layout/application "Tennis Manager" "tabs.js" USE-REACT (tabs/tabs session request)))

           ;HTML framgments - these are tabs
           (GET "/admin" {session :session} (layout/fragment "admin.js" (admin/admin session)))
           (GET "/matches" {session :session} (layout/fragment "" (match/matches session)))
           (GET "/roster" {session :session} (layout/fragment "" (rost/roster session)))
           (GET "/schedule" {session :session} (layout/fragment "schedule.js" (schedule/schedule session)))

           ;rest APIs
           (GET "/clubs" [] (rr/response (club/clubs)))
           (GET "/match-info/:match-id" [& params] (rr/response (sched/match-info (:match-id params))))
           (GET "/match-availability/:match-id" {session :session params :params} (rr/response (pr/match-availability session params)))
           (GET "/match-forfeits/:match-id" [& params] (rr/response (sched/match-forfeits (:match-id params))))
           (GET "/match-lineup/:match-id" {session :session params :params} (rr/response (pr/match-lineup session params)))
           (GET "/player/:player-id" [& params] (rr/response (player/player (:player-id params))))
           (GET "/season/:season-id" [& params] (rr/response (season/season (:season-id params))))
           (GET "/seasons" [] (rr/response (season/seasons)))
           (GET "/team/:team-id" [& params] (rr/response (team/team (:team-id params))))
           (GET "/team-info" {session :session}  (rr/response (auth_data/get-team-info-from-session-id (:identity session))))
           (GET "/team-roster/:team-id" [& params] (rr/response (team/team-roster (:team-id params))))
           (GET "/team-schedule/:season-id/:team-id" [& params] (rr/response (team/team-schedule (:season-id params) (:team-id params))))
           (GET "/team-schedule-status" {session :session}  (rr/response (team/team-schedule-status session)))
           (GET "/teams" [] (rr/response (team/teams)))

           ;POST
           (POST "/add-club" {session :session params :params} (rr/response (pr/add-club session params)))
           (POST "/add-player" {session :session params :params} (rr/response (pr/add-player session params)))
           (POST "/add-season" {session :session params :params} (rr/response (pr/add-season session params)))
           (POST "/add-team" {session :session params :params} (rr/response (pr/add-team session params)))
           (POST "/load-schedule" {session :session params :params} (rr/response (pr/load-schedule session params)))
           (POST "/update-availability" {session :session params :params} (rr/response (pr/update-player-availability session params)))
           (POST "/update-lineup" {session :session params :params} (rr/response (pr/update-lineup session params)))
           (POST "/update-player" {session :session params :params} (rr/response (pr/update-player-info session params)))
           (POST "/send-availability-email" {session :session params :params} (rr/response (em/send-avail-email session params)))
           (POST "/send-lineup-email" {session :session params :params} (rr/response (em/send-lineup-email session params)))

           (GET "/testpage" [] (restrict {:status  200
                                          :headers {"Content-Type" "text/html"}
                                          :body    "<br><br><h1 align='center'>Tennis Manager</h1><br><br><h2 align='center' style='color:red'>Test page</h2>"}
                                         {:handler  auth/any-access
                                          :on-error auth/on-error}))
           (GET "/notauth" [] {:status  200
                               :headers {"Content-Type" "text/html"}
                               :body    "<br><br><h1 align='center'>Tennis Manager</h1><br><br><h2 align='center' style='color:red'>You are not authorized to view this page</h2>"})
           (GET "/" [] {:status  200
                        :headers {"Content-Type" "text/html"}
                        :body    "<br><br><h1 align='center'>Tennis Manager</h1>"})
           ;Return an HTML page without JSON.  "Content-Type is what prevents the JSON conversion"
           (GET "/xxx" [] {:status  400
                           :headers {"Content-Type" "text/html"}
                           :body    "<h1 align='center'>Non-JSON test page</h1>"})

           (GET "/rf-test2" [] (testx))

           (GET "/rf-test" [] {:status  200
                               :headers {"Content-Type" "text/html"}
                               :body    (slurp (:body (rr/resource-response "index.html" {:root "public"})))})

           (route/not-found "Not Found"))

(def dev-handler (-> #'routes wrap-reload))

(def handler
  (-> app-routes
      (ring-json/wrap-json-response)
      (parms/wrap-params)
      (wrap-access-rules {:rules auth/rules :on-error auth/on-error})
      (wrap-authentication auth/auth-backend)
      (wrap-authorization auth/auth-backend)
      (wrap-session)
      (wrap-reload)
      (wrap-defaults (assoc-in site-defaults [:security :anti-forgery] false))))
