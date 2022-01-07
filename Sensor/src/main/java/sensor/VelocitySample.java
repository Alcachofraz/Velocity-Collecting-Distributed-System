package sensor;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.nio.charset.StandardCharsets;

public class VelocitySample {
    private final String sid;
    private final String city;
    private final String date;
    private final int velocity;

    public VelocitySample(String sid, String city, String date, int velocity) {
        this.sid = sid;
        this.city = city;
        this.date = date;
        this.velocity = velocity;
    }

    public String getSid() {
        return sid;
    }

    public String getCity() {
        return city;
    }

    public String getDate() {
        return date;
    }

    public int getVelocity() {
        return velocity;
    }

    @Override
    public String toString() {
        return "VelocitySample {SID: " + sid + ", CITY: " + city + ", DATE: " + date + ", VELOCITY: " + velocity + "}";
    }

    public byte[] toBytes() {
        Gson json = new GsonBuilder().create();
        String jsonString = json.toJson(this);
        return jsonString.getBytes(StandardCharsets.UTF_8);
    }

    public static VelocitySample fromBytes(byte[] bytes) {
        Gson json = new GsonBuilder().create();
        String jsonString = new String(bytes, StandardCharsets.UTF_8);
        return json.fromJson(jsonString, VelocitySample.class);
    }
}
