package nopainnogain.productservice.core.dto.nutrition;

import com.fasterxml.jackson.annotation.JsonProperty;
import org.springframework.lang.NonNull;

import java.time.LocalDateTime;
import java.util.UUID;

public class SaveProductDto extends ProductDto {

    @NonNull
    private final UUID uuid;
    @NonNull
    @JsonProperty("dt_create")
    private final LocalDateTime dtCreate;
    @NonNull
    @JsonProperty("dt_update")
    private final long dtUpdate;

    public SaveProductDto(String title, int weight, int calories, double proteins, double fats,
                          double carbohydrates, @NonNull UUID uuid, @NonNull LocalDateTime dtCreate, @NonNull long dtUpdate) {
        super(title, weight, calories, proteins, fats, carbohydrates);
        this.uuid = uuid;
        this.dtCreate = dtCreate;
        this.dtUpdate = dtUpdate;
    }

    @NonNull
    public UUID getUuid() {
        return uuid;
    }

    @NonNull
    public LocalDateTime getDtCreate() {
        return dtCreate;
    }

    @NonNull
    public long getDtUpdate() {
        return dtUpdate;
    }
}
