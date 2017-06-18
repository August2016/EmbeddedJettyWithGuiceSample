package sample;

import java.math.BigDecimal;

public class Pet {
    private String id;
    private String type;
    private BigDecimal price;

    public Pet(String id) {
        this.id = id;
    }

    private Pet(Builder builder) {

        id = builder.id;
        type = builder.type;
        price = builder.price;
    }

    public static Builder newBuilder() {

        return new Builder();
    }

    public String getId() {

        return id;
    }

    public String getType() {

        return type;
    }

    public BigDecimal getPrice() {

        return price;
    }


    public static final class Builder {
        private String id;
        private String type;
        private BigDecimal price;

        private Builder() {

        }

        public Builder id(String val) {

            id = val;
            return this;
        }

        public Builder type(String val) {

            type = val;
            return this;
        }

        public Builder price(BigDecimal val) {

            price = val;
            return this;
        }

        public Pet build() {

            return new Pet(this);
        }
    }
}
