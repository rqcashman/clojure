(ns tennis-manager.handler
  (:require [compojure.core :refer :all]
            [compojure.route :as route]
            [ring.middleware.defaults :refer [wrap-defaults site-defaults]]
            [ring.middleware.params :as parms]
            [ring.middleware.json :as ring-json]
            [ring.util.response :as rr]
            [tennis-manager.content.admin :as admin]
            [tennis-manager.content.matches :as match]
            [tennis-manager.content.page-layout :as layout]
            [tennis-manager.content.player-availability :as avail]
            [tennis-manager.content.roster :as rost]
            [tennis-manager.content.schedule :as sched]
            [tennis-manager.data.data-handler :as db]
            [tennis-manager.processors.service-processor :as pr]
            ))

(defroutes app-routes
           ;HTML pages
           (GET "/admin" [] (layout/application "Admin Functions" "admin.js" (admin/admin)))
           (GET "/matches" [] (layout/application "Matches" "matches.js" (match/matches)))
           (GET "/schedule" [] (layout/application "Tennis Schedule" "schedule.js" (sched/schedule)))
           (GET "/roster" [] (layout/application "Team Roster" "roster.js" (rost/roster)))

           ;rest APIs
           (GET "/clubs" [] (rr/response (db/clubs)))
           (GET "/player/:player-id" [& params] (rr/response (db/player (:player-id params))))
           (GET "/season/:season-id" [& params] (rr/response (db/season (:season-id params))))
           (GET "/seasons" [] (rr/response (db/seasons)))
           (GET "/team/:team-id" [& params] (rr/response (db/team (:team-id params))))
           (GET "/team-roster/:team-id" [& params] (rr/response (db/team-roster (:team-id params))))
           (GET "/team-schedule/:season-id/:team-id" [& params] (rr/response (db/team-schedule (:season-id params) (:team-id params))))
           (GET "/teams" [] (rr/response (db/teams)))
           (PUT "/player-availability/:available/:player-token" [& params] (layout/application "Availability Repsonse" "" (avail/update-availability  (:player-token params) (:available params))))

           ;POST
           (POST "/add-club" [& params] (rr/response (pr/add-club params)))
           (POST "/add-player" [& params] (rr/response (pr/add-player params)))
           (POST "/add-season" [& params] (rr/response (pr/add-season params)))
           (POST "/add-team" [& params] (rr/response (pr/add-team params)))
           (POST "/load-schedule" [& params] (rr/response (pr/load-schedule params)))
           (POST "/load-schedule-file" [& params] (rr/response (pr/load-schedule-file params)))
           (POST "/update-player" [& params] (rr/response (pr/update-player-info params)))
           (GET "/send-email" [& params] (rr/response (pr/send-email params)))

           (GET "/" [] "Hello World xx")
           (route/not-found "Not Found"))

(def app
  (-> app-routes
      (ring-json/wrap-json-response)
      (parms/wrap-params)
      (wrap-defaults (assoc-in site-defaults [:security :anti-forgery] false))))
