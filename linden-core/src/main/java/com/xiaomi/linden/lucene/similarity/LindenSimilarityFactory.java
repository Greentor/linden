// Copyright 2016 Xiaomi, Inc.
//
// Licensed under the Apache License, Version 2.0 (the "License");
// you may not use this file except in compliance with the License.
// You may obtain a copy of the License at
//
//     http://www.apache.org/licenses/LICENSE-2.0
//
// Unless required by applicable law or agreed to in writing, software
// distributed under the License is distributed on an "AS IS" BASIS,
// WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
// See the License for the specific language governing permissions and
// limitations under the License.

package com.xiaomi.linden.lucene.similarity;

import java.io.IOException;
import java.util.Map;

import com.google.common.base.Strings;
import org.apache.lucene.search.similarities.Similarity;

import com.xiaomi.linden.plugin.LindenPluginFactory;

public class LindenSimilarityFactory implements LindenPluginFactory<Similarity> {

  @Override
  public Similarity getInstance(Map<String, String> params) throws IOException {
    String dict = params.get("dict");
    String normLowerBound = params.get("norm");
    Similarity similarity;
    if (Strings.isNullOrEmpty(normLowerBound)) {
      similarity = new LindenSimilarity(IDFManager.createInstance(dict));
    } else {
      similarity = new LindenSimilarity(IDFManager.createInstance(dict), Float.parseFloat(normLowerBound));
    }
    return similarity;
  }
}
