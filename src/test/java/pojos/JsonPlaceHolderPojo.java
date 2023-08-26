package pojos;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)//ignore etme fonskiyonunu actik, (ignoreUnknown = false iken devre disiydi)

public class JsonPlaceHolderPojo {
    //1. Adim: Tum degiskenlere ait private variable'lar olusturulur
    private Integer userId;
    private String title;
    private Boolean completed;

    //2. Adim: Parametreli ve parametresiz constructor'lar olustur

    public JsonPlaceHolderPojo(Integer userId, String title, Boolean completed) {
        this.userId = userId;
        this.title = title;
        this.completed = completed;
    }

    public JsonPlaceHolderPojo() {
    }

    //3. Adim: Public getter ve setter methodlarini olustur

    //getter'lar parametresiz olur, setter'Lar parametreli
    //getter data türü ile(Integer,String vs.) aynı olması lazım, setter'lar void olması lazım.

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public Boolean getCompleted() {
        return completed;
    }

    public void setCompleted(Boolean completed) {
        this.completed = completed;
    }


    //4. Adim (Sart degil): toString() methodu olustur


    @Override
    public String toString() {
        return "JsonPlaceHolderPojo{" +
                "userId=" + userId +
                ", title='" + title + '\'' +
                ", completed=" + completed +
                '}';
    }
}
