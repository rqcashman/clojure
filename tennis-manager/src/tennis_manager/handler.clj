(ns tennis-manager.handler
  (:require [buddy.auth.accessrules :refer [wrap-access-rules restrict]]
            [buddy.auth.backends :as backends]
            [buddy.auth.middleware :refer [wrap-authentication wrap-authorization]]
            [compojure.core :refer :all]
            [compojure.route :as route]
            [ring.middleware.content-type :refer [wrap-content-type]]
            [ring.middleware.defaults :refer [wrap-defaults site-defaults]]
            [ring.middleware.json :as ring-json]
            [ring.middleware.params :as parms]
            [ring.middleware.session :refer [wrap-session]]
            [ring.util.response :as rr]
            [tennis-manager.content.admin :as admin]
            [tennis-manager.content.login :as login]
            [tennis-manager.content.matches :as match]
            [tennis-manager.content.page-layout :as layout]
            [tennis-manager.content.player-availability :as avail]
            [tennis-manager.content.roster :as rost]
            [tennis-manager.content.schedule :as schedule]
            [tennis-manager.content.tabs :as tabs]
            [tennis-manager.data.club-data-handler :as club]
            [tennis-manager.data.player-data-handler :as player]
            [tennis-manager.data.schedule-data-handler :as sched]
            [tennis-manager.data.season-data-handler :as season]
            [tennis-manager.data.team-data-handler :as team]
            [tennis-manager.processors.auth-processor :as auth]
            [tennis-manager.processors.send-email-processor :as em]
            [tennis-manager.processors.service-processor :as pr]))


(defroutes app-routes
           ;HTML pages
           (GET "/admin" {session :session} (layout/application "Admin Functions" "admin.js" (admin/admin session)))
           (GET "/availability-reply*" {params :params} (layout/application "Availability Response" "" (avail/update_availability (:player-token params) (:available params))))
           (GET "/chgpassword" {session :session params :params} (layout/application "Change Password" "" (login/login login/CHANGE_PASSWORD_PAGE session params)))
           (GET "/login" {session :session params :params} (layout/application "User Login" "" (login/login login/LOGIN_PAGE session params)))
           (GET "/logout" {session :session params :params} (layout/application "User Login" "" (login/login login/LOGIN_PAGE session params)))
           (GET "/matches" {session :session} (layout/application "Matches" "matches.js" (match/matches session)))
           (GET "/mgr" {session :session request :params} (layout/application "Tennis Manager" "tabs.js" (tabs/tabs session request)))
           (GET "/roster" {session :session} (layout/application "Team Roster" "roster.js" (rost/roster session)))
           (GET "/schedule" {session :session} (layout/application "Tennis Schedule" "schedule.js" (schedule/schedule session)))

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
           (GET "/team-roster/:team-id" [& params] (rr/response (team/team-roster (:team-id params))))
           (GET "/team-schedule/:season-id/:team-id" [& params] (rr/response (team/team-schedule (:season-id params) (:team-id params))))
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
                               {:handler auth/any-access
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
           (route/not-found "Not Found"))

(def app
  (-> app-routes
      (ring-json/wrap-json-response)
      (parms/wrap-params)
      (wrap-access-rules {:rules auth/rules :on-error auth/on-error})
      (wrap-authentication auth/auth-backend)
      (wrap-authorization auth/auth-backend)
      (wrap-session)
      (wrap-defaults (assoc-in site-defaults [:security :anti-forgery] false))))

