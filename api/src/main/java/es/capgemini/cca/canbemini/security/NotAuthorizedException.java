package es.capgemini.cca.canbemini.security;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@SuppressWarnings("serial")
@ResponseStatus(code = HttpStatus.FORBIDDEN, reason = "This user is not a member of this kanban!")
public class NotAuthorizedException extends Exception {
    public String msgException;

    public NotAuthorizedException(String msgException) {
        this.setMsgException(msgException);
    }

    public String getMsgException() {
        return msgException;
    }

    public void setMsgException(String msgException) {
        this.msgException = msgException;
    }
}
