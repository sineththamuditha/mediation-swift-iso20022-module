/**
 * Copyright (c) 2024, WSO2 LLC. (https://www.wso2.com).
 * <p>
 * WSO2 LLC. licenses this file to you under the Apache License,
 * Version 2.0 (the "License"); you may not use this file except
 * in compliance with the License.
 * You may obtain a copy of the License at
 * <p>
 * http://www.apache.org/licenses/LICENSE-2.0
 * <p>
 * Unless required by applicable law or agreed to in writing,
 * software distributed under the License is distributed on an
 * "AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY
 * KIND, either express or implied. See the License for the
 * specific language governing permissions and limitations
 * under the License.
 */

package org.wso2.carbon.module.swiftiso20022.mt.models.fields;

import org.wso2.carbon.module.swiftiso20022.constants.ConnectorConstants;
import org.wso2.carbon.module.swiftiso20022.constants.MTParserConstants;
import org.wso2.carbon.module.swiftiso20022.exceptions.MTMessageParsingException;

import java.util.regex.Matcher;

/**
 * Model for banking priority in User Header Block (Block 03).
 * <p>
 * example: {113:xxxx}
 *
 * @see <a href="https://www.paiementor.com/swift-mt-message-block-3-user-header-description/">
 * User Header Block Fields</a>
 */
public class Field113 {

    public static final String TAG = "113";

    // example: xxxx
    private String value;

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }

    /**
     * Method to set value of the field and return the instance.
     *
     * @param value Value to be set.
     * @return object itself
     */
    public Field113 withValue(String value) {
        setValue(value);
        return this;
    }

    /**
     * Method to parse and get Field113 object.
     *
     * @param field113String String containing value of 113 field in User Header Block
     * @return An instance of this model.
     * @throws MTMessageParsingException if the value is invalid.
     */
    public static Field113 parse(String field113String) throws MTMessageParsingException {

        Matcher field113Matcher = MTParserConstants.FIELD_113_REGEX_PATTERN.matcher(field113String);

        if (field113Matcher.matches()) {

            return new Field113()
                    .withValue(field113Matcher.group());
        } else {
            throw new MTMessageParsingException(String.format(MTParserConstants.INVALID_FIELD_IN_BLOCK_MESSAGE,
                    ConnectorConstants.BANKING_PRIORITY, ConnectorConstants.USER_HEADER_BLOCK));
        }
    }

}
