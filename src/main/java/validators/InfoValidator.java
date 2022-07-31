package validators;

import model.Info;

public class InfoValidator {

    public boolean checkAllTextField(Info info) {
        return !(info.getInfo().isEmpty());
    }
}
