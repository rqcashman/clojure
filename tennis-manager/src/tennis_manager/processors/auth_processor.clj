(ns tennis-manager.processors.auth-processor
  (:require [buddy.auth :refer [authenticated? throw-unauthorized]]
            [buddy.auth.accessrules :refer (success error)]
            [buddy.auth.backends.session :refer [session-backend]]
            [buddy.auth.middleware :refer [wrap-authentication wrap-authorization]]
            [ring.util.response :as rr]
            [tennis-manager.content.page-layout :as layout]
            [tennis-manager.content.admin :as admin]))


(defn unauthorized-handler
  [request metadata]
  (rr/response "Unauthorized request"))

(def auth-backend
  (session-backend {:unauthorized-handler unauthorized-handler}))

(defn auth-handler
  [request]
  true)
(defn admin-access
  [request]
  true)
(defn operator-access
  [request]
  true)
(defn any-access
  [request]
  true)
(defn authenticated-access
  [request]
  true)

(def rules [{:pattern    #"^/admin"
             :handler    {:or [admin-access operator-access]}
             }
            {:pattern    #"^/mgr"
             :handler    {:or [authenticated-access]}
             }
            {:pattern #"^/login$"
             :handler any-access}
            {:pattern #"^/.*"
             :handler any-access
             }
            ])

(defn on-error
  [request value]
  (println "No auth==============")
  {
   :headers {}
   :content-type "text/html"
   :body "Not authorized"})


