/*
 * Copyright 2019 TWO SIGMA OPEN SOURCE, LLC
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.twosigma.testing.examples.people;

import com.twosigma.webtau.data.table.TableData;
import org.junit.Test;

import static com.twosigma.webtau.Ddjt.actual;
import static com.twosigma.webtau.Ddjt.equal;
import static com.twosigma.webtau.Ddjt.header;

public class PeopleDaoTest {
    private PeopleDao dao = new PeopleDao();

    @Test
    public void providesAccessToNewJoiners() {
        // ...

        TableData expected = header("id", "level", "monthsAtCompany").values(
                                    "bob",      3,   0,
                                    "smith",    4,   0);

        actual(dao.thisWeekJoiners()).should(equal(expected));
    }
}