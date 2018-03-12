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
   :test ["rick" "cashman"]})

;:roster   {:loaded?     false
;           :call-status {:success? false :message "Processing..."}}
;:schedule {:loaded?     false
;           :call-status {:success? false :message "Processing..."}}
;:admin    {:loaded?     false
;           :call-status {:success? false :message "Processing..."}}})

(comment
  ":curent and :previous are a hash map {:first 'Rick' :last 'Cashman' :id 10}
   :players is a hash to the players with :id as the key {:id 10 {:first 'Rick' :last 'Cashman' :id 10}}
   :lineup uses an intelligent key :c2p1 means court 2 player 1")
(def tabbed-db
  {:team_id 33333
   :matches  {
              :loaded?     false
              :call-status {:success? true :message "Processing..."}
              :lineup      {:c1p1 {:selected {:current {:last "cash"} :previous {}}
                                   :players  {:11 {:name "cashman"}}}
                            :c1p2 {:selected {:current {} :previous {}}
                                   :players  {}}
                            :c2p1 {:selected {:current {} :previous {}}
                                   :players  {}}
                            :c2p2 {:selected {:current {} :previous {}}
                                   :players  {}}
                            :c3p1 {:selected {:current {} :previous {}}
                                   :players  {}}
                            :c3p2 {:selected {:current {} :previous {}}
                                   :players  {}}
                            :c4p1 {:selected {:current {} :previous {}}
                                   :players  {}}
                            :c4p2 {:selected {:current {} :previous {}}
                                   :players  {}}}
              :roster []
              :forfeits    {:c1 0 :c2 0 :c3 0 :c4 0}}
   :roster   {:loaded?     false
              :call-status {:success? false :message "Processing..."}}
   :schedule {:loaded?     false
              :call-status {:success? false :message "Processing..."}}
   :admin    {:loaded?     false
              :call-status {:success? false :message "Processing..."}}})