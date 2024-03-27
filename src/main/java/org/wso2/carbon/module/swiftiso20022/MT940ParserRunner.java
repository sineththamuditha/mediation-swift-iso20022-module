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

package org.wso2.carbon.module.swiftiso20022;

import org.wso2.carbon.module.swiftiso20022.mtmessageparsers.MT940Parser;
import org.wso2.carbon.module.swiftiso20022.mtmodels.mtmessages.MT940Message;

import java.nio.file.Files;
import java.nio.file.Paths;

/**
 * Class for Testing MT940 message parser
 */
public class MT940ParserRunner {
    public static void main(String[] args) throws Exception {
        String content = Files.readString(Paths.get("MT940-Test.txt"));

        MT940Message mt940Message = MT940Parser.parse(content);
    }
}
