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

package org.wso2.carbon.module.swiftiso20022.mtmodels.fields;

/**
 * Model for balance checkpoint in User Header Block (Block 03).
 *
 * format: (Date)(Time)
 */
public class Field423 extends Field {

    public static final String TAG = "423";

    // format: YYMMDD
    private String date;

    // format: HHMMSS[ss]
    // ss -> hundredths of seconds
    private String time;

    public String getDate() {
        return date;
    }

    public Field423 setDate(String date) {
        this.date = date;
        return this;
    }

    public String getTime() {
        return time;
    }

    public Field423 setTime(String time) {
        this.time = time;
        return this;
    }
}
