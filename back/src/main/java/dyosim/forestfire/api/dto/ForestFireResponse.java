package dyosim.forestfire.api.dto;

import dyosim.forestfire.api.domain.ForestFire;

import java.util.List;

public class ForestFireResponse {
    private List<ForestFire> data;

    public List<ForestFire> getData() {
        return data;
    }

    public void setData(List<ForestFire> data) {
        this.data = data;
    }
}
