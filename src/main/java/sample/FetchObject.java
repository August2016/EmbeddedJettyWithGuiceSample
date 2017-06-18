package sample;

public class FetchObject {
    public void willFetch(Domain domain) {

    }

    public Domain fetch(String xyz) {
        return new Domain(xyz);
    }
}
