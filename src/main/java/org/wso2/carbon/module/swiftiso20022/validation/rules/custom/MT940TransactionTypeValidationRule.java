/**
 * Copyright (c) 2024, WSO2 LLC. (https://www.wso2.com).
 *
 * WSO2 LLC. licenses this file to you under the Apache License,
 * Version 2.0 (the "License"); you may not use this file except
 * in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing,
 * software distributed under the License is distributed on an
 * "AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY
 * KIND, either express or implied. See the License for the
 * specific language governing permissions and limitations
 * under the License.
 */

package org.wso2.carbon.module.swiftiso20022.validation.rules.custom;

import org.apache.commons.lang3.StringUtils;
import org.wso2.carbon.module.swiftiso20022.constants.ConnectorConstants;
import org.wso2.carbon.module.swiftiso20022.validation.common.ValidationResult;
import org.wso2.carbon.module.swiftiso20022.validation.common.ValidationRule;
import org.wso2.carbon.module.swiftiso20022.validation.common.ValidatorContext;

import java.util.List;

/**
 * MT940 Transaction Type Validation Rule.
 */
public class MT940TransactionTypeValidationRule extends ValidationRule {

    private static final String RULE_NAME = "MT940 Transaction Type Validation";
    private static final int INDEX = 1;
    private static final int START_VALUE = 99;
    private static final int END_VALUE = 1000;

    private static final List<String> supportedFields = List.of(ConnectorConstants.SWIFT_TRANSFER,
            ConnectorConstants.NON_SWIFT_TRANSFER, ConnectorConstants.FIRST_ADVICE);

    public MT940TransactionTypeValidationRule(ValidatorContext context) {
        super(context);
    }

    /**
     * Validate whether the parameter Transaction type in MT940 is valid.
     * @return Validation Result
     */
    @Override
    public ValidationResult validate() {
        ValidatorContext context = super.getContext();
        String transactionType = context.getFieldValue().toString();
        boolean isSupported = supportedFields.stream().anyMatch(transactionType::startsWith);
        if (!isSupported) {
            return new ValidationResult(ConnectorConstants.ERROR_T53,
                    ConnectorConstants.ERROR_TRANS_TYPE_INVALID);
        }

        if (transactionType.startsWith(ConnectorConstants.SWIFT_TRANSFER)) {
            String identificationCode = transactionType.substring(INDEX);
            if (!StringUtils.isNumeric(identificationCode)) {
                return new ValidationResult(ConnectorConstants.ERROR_T53,
                        ConnectorConstants.ERROR_TRANS_TYPE_INVALID);
            }

            if (!(Integer.parseInt(identificationCode) > START_VALUE &&
                    Integer.parseInt(identificationCode) < END_VALUE)) {
                return new ValidationResult(ConnectorConstants.ERROR_T18,
                        ConnectorConstants.ERROR_TRANS_TYPE_INVALID);
            }
        }
        return new ValidationResult();
    }

    @Override
    public String getDisplayName() {
        return RULE_NAME;
    }
}
