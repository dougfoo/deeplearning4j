/*-
 *  * Copyright 2016 Skymind, Inc.
 *  *
 *  *    Licensed under the Apache License, Version 2.0 (the "License");
 *  *    you may not use this file except in compliance with the License.
 *  *    You may obtain a copy of the License at
 *  *
 *  *        http://www.apache.org/licenses/LICENSE-2.0
 *  *
 *  *    Unless required by applicable law or agreed to in writing, software
 *  *    distributed under the License is distributed on an "AS IS" BASIS,
 *  *    WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 *  *    See the License for the specific language governing permissions and
 *  *    limitations under the License.
 */

package org.datavec.local.transforms.analysis.aggregate;

import org.datavec.api.transform.analysis.AnalysisCounter;
import org.nd4j.linalg.function.BiFunction;

import java.util.ArrayList;
import java.util.List;

/**
 * Combine function used for undertaking analysis of a data set via Spark
 *
 * @author Alex Black
 */
public class AnalysisCombineFunction
                implements BiFunction<List<AnalysisCounter>, List<AnalysisCounter>, List<AnalysisCounter>> {
    @Override
    public List<AnalysisCounter> apply(List<AnalysisCounter> l1, List<AnalysisCounter> l2) {
        if (l1 == null)
            return l2;
        if (l2 == null)
            return l1;

        int size = l1.size();
        if (size != l2.size())
            throw new IllegalStateException("List lengths differ");

        List<AnalysisCounter> out = new ArrayList<>();
        for (int i = 0; i < size; i++) {
            out.add(l1.get(i).merge(l2.get(i)));
        }
        return out;
    }
}
