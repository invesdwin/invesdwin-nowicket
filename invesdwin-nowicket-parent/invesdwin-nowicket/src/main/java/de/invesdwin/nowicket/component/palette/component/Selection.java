/*
 * Licensed to the Apache Software Foundation (ASF) under one or more contributor license agreements. See the NOTICE
 * file distributed with this work for additional information regarding copyright ownership. The ASF licenses this file
 * to You under the Apache License, Version 2.0 (the "License"); you may not use this file except in compliance with the
 * License. You may obtain a copy of the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software distributed under the License is distributed on
 * an "AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied. See the License for the
 * specific language governing permissions and limitations under the License.
 */
package de.invesdwin.nowicket.component.palette.component;

import java.util.Iterator;

import javax.annotation.concurrent.NotThreadSafe;

import org.apache.wicket.markup.ComponentTag;
import org.apache.wicket.util.value.IValueMap;

import de.invesdwin.nowicket.component.palette.Palette;

/**
 * select box containg selected choices of the palette
 * 
 * @param <T>
 * @author Igor Vaynberg ( ivaynberg )
 */
@NotThreadSafe
public class Selection<T> extends AOptions<T> {
    private static final long serialVersionUID = 1L;

    /**
     * @param id
     *            component id
     * @param palette
     *            parent palette
     */
    public Selection(final String id, final Palette<T> palette) {
        super(id, palette);
    }

    @Override
    protected void onComponentTag(final ComponentTag tag) {
        super.onComponentTag(tag);
        final IValueMap attrs = tag.getAttributes();

        final String onFocus = getPalette().getSelectionOnFocusJS();
        if (onFocus != null) {
            attrs.put("onfocus", onFocus);
        }

        tag.getAttributes().put("ondblclick", getPalette().getRemoveOnClickJS());
    }

    @Override
    protected Iterator<T> getOptionsIterator() {
        return getPalette().getSelectedChoices();
    }

}
