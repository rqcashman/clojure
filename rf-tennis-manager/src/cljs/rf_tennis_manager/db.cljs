(ns rf-tennis-manager.db)

(def default-dbx
  {:name "Cashman"
   :players
         {:11 {:last "Cashman" :first "Rick" :id 11}
          :12 {:last "Flowers" :first "Henry" :id 12}}})

(def default-db
  {:matches {
             :loaded?     false
             :call-status {:success? false :message "Processing..."}
             :lineup      {:c1p1     {:selected {:current {} :previous {}}
                                      :players  {
                                                 :0 {:last "-- none --" :first "" :id 0}
                                                 :1 {:last "Cashman" :first "Rick" :id 1}
                                                 :2 {:last "Flowers" :first "Henry" :id 2}
                                                 :3 {:last "Barnhill" :first "Bruce" :id 3}
                                                 :4 {:last "Allen" :first "Mark" :id 4}}}

                           :c1p2     {:selected {:current {} :previous {}}
                                      :players  {
                                                 :0 {:last "-- none --" :first "" :id 0}
                                                 :1 {:last "Cashman" :first "Rick" :id 1}
                                                 :2 {:last "Flowers" :first "Henry" :id 2}
                                                 :3 {:last "Barnhill" :first "Bruce" :id 3}
                                                 :4 {:last "Allen" :first "Mark" :id 4}}}

                           :c1p3     {:selected {:current {} :previous {}}
                                      :players  {
                                                 :0 {:last "-- none --" :first "" :id 0}
                                                 :1 {:last "Cashman" :first "Rick" :id 1}
                                                 :2 {:last "Flowers" :first "Henry" :id 2}
                                                 :3 {:last "Barnhill" :first "Bruce" :id 3}
                                                 :4 {:last "Allen" :first "Mark" :id 4}}}

                           :forfeits {:c1 0 :c2 0 :c3 0 :c4 0}}}
   :test    ["rick" "cashman"]})

(def default-player {:last_name "----- none -----" :first_name "" :id 0})
(comment
  ":curent and :previous are a hash map {:first 'Rick' :last 'Cashman' :id 10}
   :players is a hash to the players with :id as the key {:id 10 {:first 'Rick' :last 'Cashman' :id 10}}
   :lineup uses an intelligent key :c2p1 means court 2 player 1")
(def tabbed-db
  {:matches  {:panel-visible      {:send-avail-email  false
                                   :availability      false
                                   :set-lineup        false
                                   :send-lineup-email false
                                   :call-status       false
                                   :schedule          true}
              :loaded?            false
              :call-status        {:success? true :message "Processing..." :on-click nil}
              :lineup-player-list {:c1p1 {:selected default-player
                                          :players  {(keyword (str (:id default-player))) default-player}}
                                   :c1p2 {:selected default-player
                                          :players  {(keyword (str (:id default-player))) default-player}}
                                   :c2p1 {:selected default-player
                                          :players  {(keyword (str (:id default-player))) default-player}}
                                   :c2p2 {:selected default-player
                                          :players  {(keyword (str (:id default-player))) default-player}}
                                   :c3p1 {:selected default-player
                                          :players  {(keyword (str (:id default-player))) default-player}}
                                   :c3p2 {:selected default-player
                                          :players  {(keyword (str (:id default-player))) default-player}}
                                   :c4p1 {:selected default-player
                                          :players  {(keyword (str (:id default-player))) default-player}}
                                   :c4p2 {:selected default-player
                                          :players  {(keyword (str (:id default-player))) default-player}}}
              :roster             []
              :forfeits           {:c1 0 :c2 0 :c3 0 :c4 0}}
   :roster   {:panel-visible          {:roster        false
                                       :add-player    false
                                       :update-player false
                                       :call-status   false}
              :add-update             {:fields {:first-name   {:name "First name" :value "" :type "text" :required? true :min-length 2 :valid? true :error-msg ""}
                                                :last-name    {:name "Last name" :value "" :type "text" :required? true :min-length 2 :valid? true :error-msg ""}
                                                :email        {:name "Email" :value "" :type "email" :required? false :min-length 4 :valid? true :error-msg ""}
                                                :phone-number {:name "Phone number" :value "" :type "phone-number" :required? false :valid? true :error-msg ""}
                                                :status       {:name "Status" :value "A" :type "select" :required? true :valid? true :error-msg ""}}}
              :loaded?                false
              :selected-roster-action ""
              :selected-team          {:id 1 :name "Team name"}
              :selected-player        {:id 0 :last_name "" :first_name "" :status "A"}
              :team-roster            [{:id 1 :last_name "cashman" :first_name "rick"}]
              :call-status            {:success? true :message "Processing..." :on-click nil}}
   :teams    {:list [{:id 0 :name "team name"}]}
   :season   "rick cashman"
   :schedule {:panel-visible {:select-form true
                              :schedule    false
                              :call-status false}
              :loaded?       false
              :call-status   {:success? true :message "Processing..." :on-click nil}}
   :admin    {:panel-visible {:add-club      true
                              :add-season    false
                              :add-team      false
                              :load-schedule false
                              :call-status   false}
              :add-season    {}
              :add-team      {:fields {:team-name    {:name "Team name" :value "" :type "text" :required? true :min-length 2 :valid? true :error-msg ""}
                                       :sched-abbrev {:name "Schedule abbreviation" :value "" :type "text" :required? true :min-length 2 :max-length 10 :valid? true :error-msg ""}}}
              :add-club      {:fields {:club-name    {:name "Club name" :value "" :type "text" :required? true :min-length 2 :max-length 45 :valid? true :error-msg ""}
                                       :street       {:name "Street address" :value "" :type "text" :required? true :min-length 5 :max-length 100 :valid? true :error-msg ""}
                                       :city         {:name "City" :value "" :type "text" :required? true :min-length 2 :max-length 45 :valid? true :error-msg ""}
                                       :state        {:name "State" :value "Ohio" :type "text" :required? true :valid? true :error-msg ""}
                                       :zip-code     {:name "Zip code" :value "" :type "numeric" :required? true :min-length 5 :max-length 5 :valid? true :error-msg ""}
                                       :phone-number {:name "Phone number" :value "" :type "phone-number" :required? true :valid? true :error-msg ""}}}
              :loaded?       false
              :call-status   {:success? true :message "Processing..." :on-click nil}}})