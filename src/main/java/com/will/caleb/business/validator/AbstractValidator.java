package com.will.caleb.business.validator;

import com.will.caleb.business.exception.CustomException;
import com.will.caleb.business.utils.NumericUtil;
import com.will.caleb.business.utils.Utils;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.IntStream;

public abstract class AbstractValidator {

    Map<String, Object> toValidateMap = new HashMap<>();

    public void addFieldToValidate(String fieldName, Object toValidate) {
        if (Utils.isEmpty(toValidate)) {
            toValidateMap.put(fieldName, toValidate);
        }
    }

    public void validate() {
        if (!toValidateMap.isEmpty()) {

            List<String> args = new ArrayList<>();

            toValidateMap.forEach((key, value) -> args.add(key));

            if (!args.isEmpty()) {
                StringBuilder message = new StringBuilder("Os seguintes campos são obrigatórios: { ");

                IntStream.range(0, args.size()).forEach(i -> {
                    message.append(args.get(i));
                    if (NumericUtil.isLess(i, args.size() -1)) {
                        message.append(", ");
                    }
                });
                message.append(" }");

                throw new CustomException(message.toString());
            }
        }
    }
}
