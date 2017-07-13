package subscription.restApi.entityDTO;

public class ErrorInfoDTO {


    private String errorMessage;

    public ErrorInfoDTO(String errorMessage){
        this.errorMessage = errorMessage;
    }

    public String getErrorMessage() {
        return errorMessage;
    }

}