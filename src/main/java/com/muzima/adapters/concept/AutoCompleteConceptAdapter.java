/**
 * Copyright 2012 Muzima Team
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
package com.muzima.adapters.concept;

import android.content.Context;
import android.util.Log;
import android.widget.AutoCompleteTextView;

import com.muzima.api.model.Concept;
import com.muzima.controller.ConceptController;

import java.util.ArrayList;
import java.util.List;

/**
 * Responsible to display and select auto-complete menu of Concept while adding new Concept.
 */

public class AutoCompleteConceptAdapter extends AutoCompleteBaseAdapter<Concept> {
    private static final String TAG = AutoCompleteConceptAdapter.class.getSimpleName();

    public AutoCompleteConceptAdapter(Context context, int textViewResourceId, AutoCompleteTextView autoCompleteConceptTextView) {
        super(context, textViewResourceId, autoCompleteConceptTextView);
    }

    @Override
    protected List<Concept> getOptions(CharSequence constraint) {
        ConceptController conceptController = getMuzimaApplicationContext().getConceptController();
        try {
            return conceptController.downloadConceptsByNamePrefix(constraint.toString());
        } catch (ConceptController.ConceptDownloadException e) {
            Log.e(TAG, "Unable to download concepts!", e);
        }
        return new ArrayList<Concept>();
    }

    @Override
    protected String getOptionName(Concept concept) {
        return concept.getName();
    }
}
