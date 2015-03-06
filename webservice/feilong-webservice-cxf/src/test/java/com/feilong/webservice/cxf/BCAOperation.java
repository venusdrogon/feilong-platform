/*
 * Copyright (C) 2008 feilong (venusdrogon@163.com)
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *         http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package com.feilong.webservice.cxf;

import javax.jws.WebService;

/*
 * Copyright (C) 2008 feilong (venusdrogon@163.com)
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *         http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

/**
 * The Interface BCAOperation.
 * 
 * @author <a href="mailto:venusdrogon@163.com">金鑫</a>
 * @version 1.0 2014年5月5日 下午5:12:05
 */
@WebService
public interface BCAOperation{

    /**
     * Transaction query.
     * 
     * @param merchantTransactionID
     *            the merchant transaction id
     * @param serviceVersion
     *            the service version
     * @param siteID
     *            the site id
     * @param transactionID
     *            the transaction id
     * @param transactionType
     *            the transaction type
     * @return the string
     */
    public String transactionQuery(
                    String merchantTransactionID,
                    String serviceVersion,
                    String siteID,
                    String transactionID,
                    String transactionType);
}
