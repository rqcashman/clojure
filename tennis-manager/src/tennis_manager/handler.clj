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
            [tennis-manager.content.schedule :as schedule]
            [tennis-manager.data.club-data-handler :as club]
            [tennis-manager.data.player-data-handler :as player]
            [tennis-manager.data.schedule-data-handler :as sched]
            [tennis-manager.data.season-data-handler :as season]
            [tennis-manager.data.team-data-handler :as team]
            [tennis-manager.processors.service-processor :as pr]))

(defroutes app-routes
           ;HTML pages
           (GET "/admin" [] (layout/application "Admin Functions" "admin.js" (admin/admin)))
           (GET "/matches" [] (layout/application "Matches" "matches.js" (match/matches)))
           (GET "/schedule" [] (layout/application "Tennis Schedule" "schedule.js" (schedule/schedule)))
           (GET "/roster" [] (layout/application "Team Roster" "roster.js" (rost/roster)))
           (GET "/availability-reply*" {params :query-params} (layout/application "Availability Response" "blank.js" (avail/update_availability (get  params "player-token") (get  params "available"))))
           ;(GET "/availability-reply/:available/:player-token" [& params] (layout/application "Availability Response" "" (avail/update_availability)))

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
           (POST "/send-availability-email" [& params] (rr/response (pr/send-avail-email params)))

           (GET "/" [] "Hello World")
           (route/not-found "Not Found"))

(def app
  (-> app-routes
      (ring-json/wrap-json-response)
      (parms/wrap-params)
      (wrap-defaults (assoc-in site-defaults [:security :anti-forgery] false))))
