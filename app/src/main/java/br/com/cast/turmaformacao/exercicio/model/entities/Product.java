package br.com.cast.turmaformacao.exercicio.model.entities;

import android.os.Parcel;
import android.os.Parcelable;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;


public class Product implements Parcelable {

    @JsonIgnore
    private Long id;

    @JsonProperty("image")
    private String image;

    @JsonProperty("id")
    private Long idWeb;

    @JsonProperty("date")
    private Long date;

    @JsonProperty("name")
    private String name;

    @JsonProperty("description")
    private String description;

    @JsonProperty("stock")
    private Long amount;

    @JsonProperty("minimunStock")
    private Long amountMin;

    @JsonProperty("unitaryValue")
    private Double unitaryValue;


    public Product(){
        super();
    }

    public Product(Parcel imp) {
        super();
        readFromParcel(imp);
    }


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public Long getIdWeb() {
        return idWeb;
    }

    public void setIdWeb(Long idWeb) {
        this.idWeb = idWeb;
    }

    public Long getDate() {
        return date;
    }

    public void setDate(Long date) {
        this.date = date;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Long getAmount() {
        return amount;
    }

    public void setAmount(Long amount) {
        this.amount = amount;
    }

    public Long getAmountMin() {
        return amountMin;
    }

    public void setAmountMin(Long amountMin) {
        this.amountMin = amountMin;
    }

    public Double getUnitaryValue() {
        return unitaryValue;
    }

    public void setUnitaryValue(Double unitaryValue) {
        this.unitaryValue = unitaryValue;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Product product = (Product) o;

        if (!id.equals(product.id)) return false;
        if (!idWeb.equals(product.idWeb)) return false;
        if (!date.equals(product.date)) return false;
        if (!image.equals(product.image)) return false;
        if (!name.equals(product.name)) return false;
        if (!description.equals(product.description)) return false;
        if (!amount.equals(product.amount)) return false;
        if (!amountMin.equals(product.amountMin)) return false;
        return unitaryValue.equals(product.unitaryValue);

    }

    @Override
    public int hashCode() {
        int result = id.hashCode();
        result = 31 * result + idWeb.hashCode();
        result = 31 * result + date.hashCode();
        result = 31 * result + image.hashCode();
        result = 31 * result + name.hashCode();
        result = 31 * result + description.hashCode();
        result = 31 * result + amount.hashCode();
        result = 31 * result + amountMin.hashCode();
        result = 31 * result + unitaryValue.hashCode();
        return result;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeLong(id == null ? -1 : id);
        dest.writeLong(idWeb == null ? -1 : idWeb);
        dest.writeLong(date == null ? -1 : date);
        dest.writeString(image == null ? "" : image);
        dest.writeString(name == null ? "" : name);
        dest.writeString(description == null ? "" : description);
        dest.writeLong(amount == null ? -1 : amount);
        dest.writeLong(amountMin == null ? -1 : amountMin);
        dest.writeDouble(unitaryValue == 0 ? 0 : unitaryValue);
    }

    public void readFromParcel(Parcel imp) {

        id = imp.readLong();
        id = id == -1 ? null : id;

        idWeb = imp.readLong();
        idWeb = idWeb == -1 ? null : idWeb;

        date = imp.readLong();
        date = date == -1 ? null : date;

        image = imp.readString();
        image = image == "" ? null : image;

        name = imp.readString();
        name = name == "" ? null : name;

        description = imp.readString();
        description = description == "" ? null : description;

        amount = imp.readLong();
        amount = amount == -1 ? null : amount;

        amountMin = imp.readLong();
        amountMin = amountMin == -1 ? null : amountMin;

        unitaryValue = imp.readDouble();
        unitaryValue = unitaryValue == 0 ? null : unitaryValue;
    }

    public static final Parcelable.Creator<Product> CREATOR = new Parcelable.Creator<Product>() {

        @Override
        public Product createFromParcel(Parcel source) {
            return new Product(source);
        }

        @Override
        public Product[] newArray(int size) {
            return new Product[size];
        }
    };

    @Override
    public String toString() {
        return "Product{" +
                "id=" + id +
                ", image='" + image + '\'' +
                ", idWeb=" + idWeb +
                ", date=" + date +
                ", name='" + name + '\'' +
                ", description='" + description + '\'' +
                ", amount=" + amount +
                ", amountMin=" + amountMin +
                ", unitaryValue=" + unitaryValue +
                '}';
    }
}
