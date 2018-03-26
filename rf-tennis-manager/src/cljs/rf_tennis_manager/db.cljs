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
                                                 :4 {:last "Allen" :first "Mark" :id 4}
                                                 }}
                           :c1p2     {:selected {:current {} :previous {}}
                                      :players  {
                                                 :0 {:last "-- none --" :first "" :id 0}
                                                 :1 {:last "Cashman" :first "Rick" :id 1}
                                                 :2 {:last "Flowers" :first "Henry" :id 2}
                                                 :3 {:last "Barnhill" :first "Bruce" :id 3}
                                                 :4 {:last "Allen" :first "Mark" :id 4}
                                                 }}
                           :c1p3     {:selected {:current {} :previous {}}
                                      :players  {
                                                 :0 {:last "-- none --" :first "" :id 0}
                                                 :1 {:last "Cashman" :first "Rick" :id 1}
                                                 :2 {:last "Flowers" :first "Henry" :id 2}
                                                 :3 {:last "Barnhill" :first "Bruce" :id 3}
                                                 :4 {:last "Allen" :first "Mark" :id 4}
                                                 }}
                           :forfeits {:c1 0 :c2 0 :c3 0 :c4 0}}}
   :test    ["rick" "cashman"]})

;:roster   {:loaded?     false
;           :call-status {:success? false :message "Processing..."}}
;:schedule {:loaded?     false
;           :call-status {:success? false :message "Processing..."}}
;:admin    {:loaded?     false
;           :call-status {:success? false :message "Processing..."}}})
()
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
   :roster   {:loaded?     false
              :call-status {:success? false :message "Processing..."}}
   :schedule {:loaded?     false
              :call-status {:success? false :message "Processing..."}}
   :admin    {:loaded?     false
              :call-status {:success? false :message "Processing..."}}})