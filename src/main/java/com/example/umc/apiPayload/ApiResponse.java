package com.example.umc.apiPayload;

import com.example.umc.apiPayload.code.BaseCode;
import com.example.umc.apiPayload.code.status.SuccessStatus;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
@JsonPropertyOrder({"isSuccess", "code", "message", "result"})
public class ApiResponse<T> {

    @JsonProperty("isSuccess")
    private final Boolean isSuccess;
    private final String code;
    private final String message;
    @JsonInclude(JsonInclude.Include.NON_NULL)      // result 값은 null이 아닐 때만 응답에 포함시킨다는 뜻
    private T result;


    /*
    첫 번째 T: 반환 유형을 나타냅니다.

    이것은 메서드가 반환하는 ApiResponse 객체가 어떤 유형의 데이터를 포함하는지를 나타냅니다.
    예를 들어, ApiResponse<String>, ApiResponse<Integer>, ApiResponse<SomeObject> 등과 같이 다양한 데이터 유형을 반환할 수 있습니다.
    두 번째 T: 메서드의 매개변수 유형을 나타냅니다.

    이것은 onSuccess 메서드에 전달되는 데이터의 유형을 나타냅니다.
    즉, 메서드가 성공했을 때 이 메서드에 전달되는 데이터의 유형을 나타냅니다.
    예를 들어, onSuccess("Success!"), onSuccess(100), onSuccess(someObject)와 같이 다양한 유형의 데이터를 전달할 수 있습니다.
    세 번째 T: 두 번째 T와 동일한 것입니다.

    위에서 설명한 것처럼 실제로는 두 번째 T와 동일한 의미를 가집니다.
    세 번째 T는 메서드 내부에서 사용되는 데이터의 유형을 나타냅니다. 따라서 두 번째 T와 동일한 것입니다.
    이렇게 하여 onSuccess 메서드는 다양한 유형의 데이터를 받아들이고 처리한 후 해당 유형에 맞는 ApiResponse를 반환할 수 있습니다.
     */


    // 성공한 경우 응답 생성
    public static <T> ApiResponse<T> onSuccess(T result){
        return new ApiResponse<>(true, SuccessStatus._OK.getCode() , SuccessStatus._OK.getMessage(), result);
    }


    // 실패한 경우 응답 생성
    public static <T> ApiResponse<T> onFailure(String code, String message, T data){
        return new ApiResponse<>(false, code, message, data);
    }
}
