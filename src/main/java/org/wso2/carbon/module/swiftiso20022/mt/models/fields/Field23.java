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

import java.util.Arrays;
import java.util.List;
import java.util.regex.Matcher;

/**
 * 23B -> Model for bank operation code in Text Block (Block 04).
 * Value can only be one of predefined 4 character codes.
 * <p>
 * example: :23B:SPAY
 * <p>
 * 23E -> Model for instruction code in Text Block (Block 04).
 * <p>
 * format: (Instruction Code)/[Additional Information]
 * <p>
 * example: TELI/3226553478
 *
 * @see <a href="https://www2.swift.com/knowledgecentre/publications/usgf_20230720/2.0?topic=idx_fld_tag_23B.htm">
 * Field 23B</a>
 * @see <a href="https://www2.swift.com/knowledgecentre/publications/usgf_20230720/2.0?topic=idx_fld_tag_23E.htm">
 * Field 23E</a>
 */
public class Field23 {

    public static final String TAG = "23";

    /**
     * List of options with current implementation.
     */
    private static final List<Character> OPTIONS = Arrays.asList(
            ConnectorConstants.OPTION_B, ConnectorConstants.OPTION_E);
    private char option;

    // example: TELI
    private String code;

    // example: 3226553478
    private String additionalInformation;

    /**
     * Constructor to initialize all attributes.
     *
     * @param option Single character
     * @param code String with four uppercase letters
     * @param additionalInformation Optional string with character set x
     */
    public Field23(char option, String code, String additionalInformation) {
        this.option = option;
        this.code = code;
        this.additionalInformation = additionalInformation;
    }

    public char getOption() {
        return option;
    }

    public void setOption(char option) {
        this.option = option;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getAdditionalInformation() {
        return additionalInformation;
    }

    public void setAdditionalInformation(String additionalInformation) {
        this.additionalInformation = additionalInformation;
    }

    /**
     * Method to parse and get Field23 object.
     * Current Implementations -> Option B and E
     *
     * @param option        single character option of the field23String
     * @param field23String String containing value of 23 field in Text Block
     * @return An instance of this model.
     * @throws MTMessageParsingException if the value is invalid
     */
    public static Field23 parse(char option, String field23String) throws MTMessageParsingException {

        if (!OPTIONS.contains(option)) {
            throw new MTMessageParsingException(String.format(MTParserConstants.INVALID_OPTION_FOR_FIELD, option,
                    ConnectorConstants.FIELD_23));
        }

        Matcher field23Matcher = MTParserConstants.FIELD_23_REGEX_PATTERN.matcher(field23String);

        if (field23Matcher.matches()) {
            // group 1 -> Code
            // group 2 -> /Additional Information
            // group 3 -> Additional Information
            return new Field23(option, field23Matcher.group(1), field23Matcher.group(3));
        } else {
            throw new MTMessageParsingException(String.format(MTParserConstants.INVALID_FIELD_IN_BLOCK_MESSAGE,
                    ConnectorConstants.FIELD_23 + (option == ConnectorConstants.NO_LETTER_OPTION ? "" : option),
                    ConnectorConstants.TEXT_BLOCK));
        }
    }
}
