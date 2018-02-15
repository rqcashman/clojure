(ns tennis-manager.handler
  (:require [buddy.auth.accessrules :refer [wrap-access-rules]]
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
           (GET "/login"  {params :query-params}  (layout/application "User Login" "" (login/login (get params "err") (get params "username") (get params "msg"))))
           (GET "/mgr" {session :session} (layout/application "Tennis Manager" "tabs.js" (tabs/tabs session)))
           (GET "/admin" {session :session} (layout/application "Admin Functions" "admin.js" (admin/admin session)))
           (GET "/matches" {session :session} (layout/application "Matches" "matches.js" (match/matches session)))
           (GET "/schedule" {session :session}(layout/application "Tennis Schedule" "schedule.js" (schedule/schedule session)))
           (GET "/roster" {session :session}(layout/application "Team Roster" "roster.js" (rost/roster session)))
           (GET "/availability-reply*" {params :query-params} (layout/application "Availability Response" "" (avail/update_availability (get params "player-token") (get params "available"))))

           ;rest APIs
           (GET "/clubs" [] (rr/response (club/clubs)))
           (GET "/match-info/:match-id" [& params] (rr/response (sched/match-info (:match-id params))))
           (GET "/match-availability/:match-id" [& params] (rr/response (sched/match-availability (:match-id params))))
           (GET "/match-forfeits/:match-id" [& params] (rr/response (sched/match-forfeits (:match-id params))))
           (GET "/match-lineup/:match-id" [& params] (rr/response (sched/match-lineup (:match-id params))))
           (GET "/player/:player-id" [& params] (rr/response (player/player (:player-id params))))
           (GET "/season/:season-id" [& params] (rr/response (season/season (:season-id params))))
           (GET "/seasons" [] (rr/response (season/seasons)))
           (GET "/team/:team-id" [& params] (rr/response (team/team (:team-id params))))
           (GET "/team-roster/:team-id" [& params] (rr/response (team/team-roster (:team-id params))))
           (GET "/team-schedule/:season-id/:team-id" [& params] (rr/response (team/team-schedule (:season-id params) (:team-id params))))
           (GET "/teams" [] (rr/response (team/teams)))

           ;POST
           (POST "/add-club" [& params] (rr/response (pr/add-club params)))
           (POST "/add-player" [& params] (rr/response (pr/add-player params)))
           (POST "/add-season" [& params] (rr/response (pr/add-season params)))
           (POST "/add-team" [& params] (rr/response (pr/add-team params)))
           (POST "/load-schedule" [& params] (rr/response (pr/load-schedule params)))
           (POST "/load-schedule-file" [& params] (rr/response (pr/load-schedule-file params)))
           (POST "/update-availability" [& params] (rr/response (pr/update-player-availability params)))
           (POST "/update-lineup" [& params] (rr/response (pr/update-lineup params)))
           (POST "/update-player" [& params] (rr/response (pr/update-player-info params)))
           (POST "/send-availability-email" [& params] (rr/response (em/send-avail-email params)))
           (POST "/send-lineup-email" [& params] (rr/response (em/send-lineup-email params)))
           (POST "/login" [& params] "Login post page")

           (GET "/noauth" [] {:status  200
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

