(ns tennis-manager.data.system-info)
;plan is to add logins which will give us the team id
;for now we will just use a hared-code team id
(def email-cred {:user     "rqcashman@gmail.com"
                 :password "oitdgcoxpdghplmb"})

(def db-cred
  {:dbtype   "mysql"
   :dbname   "tennis_manager"
   :host     "localhost"
   :port     3306
   :user     "root"
   :useSSL true
   :password "Cash1234"})
