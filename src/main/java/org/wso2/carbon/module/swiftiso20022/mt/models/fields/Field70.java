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
 * Model for remittance information in Text Block (Block 04).
 *
 * <dl>
 *     <dt>format:</dt>
 *     <dd>4*(Details)</dd>
 *
 *     <dt>example:</dt>
 *     <dd>:70:/INV/abc/SDF-96//1234-234///ROC/98I</dd>
 *     <dd>U87</dd>
 * </dl>
 *
 * @see <a href="https://www2.swift.com/knowledgecentre/publications/usgf_20230720/2.0?topic=idx_fld_tag_70.htm">
 * Field 70</a>
 */
public class Field70 {

    public static final String TAG = "70";

    /**
     * List of options with current implementation.
     */
    private static final List<Character> OPTIONS = Arrays.asList(ConnectorConstants.NO_LETTER_OPTION);
    private char option;
    private List<String> values;

    /**
     * Constructor to initialize all attributes.
     *
     * @param option single character which identify the option.
     * @param values String array with each value is a separate line
     */
    public Field70(char option, List<String> values) {
        this.option = option;
        this.values = values;
    }

    public List<String> getValues() {
        return values;
    }

    public char getOption() {
        return option;
    }

    public void setOption(char option) {
        this.option = option;
    }

    public void setValues(List<String> values) {
        this.values = values;
    }

    /**
     * Method to parse and get Field70 object.
     * Current implementations -> No_letter
     *
     * @param option single character option of the field70String
     * @param field70String String containing value of 70 field in Text Block
     * @return An instance of this model.
     * @throws MTMessageParsingException if the value is invalid
     */
    public static Field70 parse(char option, String field70String) throws MTMessageParsingException {

        if (!OPTIONS.contains(option)) {
            throw new MTMessageParsingException(String.format(
                    MTParserConstants.INVALID_OPTION_FOR_FIELD, option, ConnectorConstants.FIELD_70));
        }

        Matcher field70Matcher = MTParserConstants.FIELD_70_REGEX_PATTERN.matcher(field70String);

        if (field70Matcher.matches()) {
            return new Field70(option,
                    // Values group -> "line1\nline2\n" -> ["line1", "line2"]
                    Arrays.asList(field70Matcher.group().split(MTParserConstants.LINE_BREAK_REGEX_PATTERN)));
        } else {
            throw new MTMessageParsingException(String.format(MTParserConstants.INVALID_FIELD_IN_BLOCK_MESSAGE,
                    ConnectorConstants.FIELD_70 + (option == ConnectorConstants.NO_LETTER_OPTION ? "" : option),
                    ConnectorConstants.TEXT_BLOCK));
        }
    }
}
