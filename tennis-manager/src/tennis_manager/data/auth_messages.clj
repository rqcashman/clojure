(ns tennis-manager.data.auth-messages)

(def LOGIN_SUCCESS "0")
(def LOGOUT_SUCCESS "100")
(def CHG_PASSWORD_SUCCESS "200")
(def LOGIN_FAILED "1000")
(def LOGIN_LOCKED "1001")
(def LOGIN_DISABLED "1002")
(def CHG_PASSWORD "1003")
(def SESSION_EXPIRED "1004")
(def NOT_AUTHENTICATED "1005")
(def NOT_AUTHORIZED "1006")
(def LOGIN_FAILED_CHG_PWD "1007")
(def CHG_PASSWORD_TOO_SHORT "1008")
(def CHG_PASSWORDS_DO_NOT_MATCH "1009")
(def CHG_PASSWORD_SAME "1010")
(def CHG_PASSWORD_COMPLEXITY_FAIL "1011")

(def ADMIN_USER 1)
(def PASSWORD_MIN_LENGTH 8)

(def special-chars "!@#$%&*")
(def NOT_AUTHORIZED_MSG "Not Authorized")
(def password-complexity-error
  (str "New password must contain at least<span style='color:blue'>
        <br>&nbsp;&nbsp;&nbsp;&nbsp;1 upper case
        <br>&nbsp;&nbsp;&nbsp;&nbsp;2 lower case
        <br>&nbsp;&nbsp;&nbsp;&nbsp;1 number
        <br>&nbsp;&nbsp;&nbsp;&nbsp;1 special character</span>
        <span style='color:purple'> " special-chars "</span>"))

(def auth-errors
  {(keyword LOGIN_SUCCESS)                {:url "/mgr"}
   (keyword LOGOUT_SUCCESS)               {:url "/login" :msg "<span style='color:green;font-weight:bold'>Logout successful</span>"}
   (keyword LOGIN_FAILED)                 {:url "/login" :msg "Login failed"}
   (keyword LOGIN_LOCKED)                 {:url "/login" :msg "Account locked"}
   (keyword LOGIN_DISABLED)               {:url "/login" :msg "Account disabled"}
   (keyword CHG_PASSWORD)                 {:url "/chgpassword" :msg "Password change required"}
   (keyword SESSION_EXPIRED)              {:url "/login" :msg "Login session timed out due to inactivity"}
   (keyword NOT_AUTHENTICATED)            {:url "/login"}
   (keyword NOT_AUTHORIZED)               {:url "/notauth"}
   (keyword LOGIN_FAILED_CHG_PWD)         {:url "/chgpassword" :msg "Password invalid"}
   (keyword CHG_PASSWORD_TOO_SHORT)       {:url "/chgpassword" :msg (str "New password must be a least " PASSWORD_MIN_LENGTH " characters long")}
   (keyword CHG_PASSWORDS_DO_NOT_MATCH)   {:url "/chgpassword" :msg "Passwords do not match"}
   (keyword CHG_PASSWORD_SAME)            {:url "/chgpassword" :msg "New password cannot be the same as the current password"}
   (keyword CHG_PASSWORD_COMPLEXITY_FAIL) {:url "/chgpassword" :msg password-complexity-error}
   (keyword CHG_PASSWORD_SUCCESS)         {:url "/login" :msg "Password changed"}})

(comment "if an entry has a msg we add the errno to it.
          This allows us just to send the errno to the redirected page without
          having to manually add the errno to the value array which could lead to errors")
(defn add-error-no
  "Add errno to value map if there is a msg key"
  [errlist err]
  (if (contains? (val err) :msg)
    (conj errlist {(key err) (assoc (val err) :errno (subs (str (key err)) 1))})
    (conj errlist err)))

(def authentication-error-list (reduce #(add-error-no %1 %2) {} auth-errors))