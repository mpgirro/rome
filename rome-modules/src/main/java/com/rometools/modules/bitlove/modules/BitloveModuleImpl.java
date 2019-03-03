/*
 * Copyright 2019 Maximilian Irro
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package com.rometools.modules.bitlove.modules;


import com.rometools.rome.feed.CopyFrom;
import com.rometools.rome.feed.impl.EqualsBean;
import com.rometools.rome.feed.impl.ToStringBean;
import com.rometools.rome.feed.module.ModuleImpl;

import java.io.Serializable;

public class BitloveModuleImpl
        extends ModuleImpl
        implements BitloveModule, Cloneable, Serializable {

    private String guid;

    public BitloveModuleImpl() {
        super(BitloveModule.class, BitloveModule.URI);
    }

    @Override
    public String getGuid() {
        return this.guid;
    }

    @Override
    public void setGuid(String guid) {
        this.guid = guid;
    }


    @Override
    public Class<? extends CopyFrom> getInterface() {
        return BitloveModule.class;
    }

    @Override
    public void copyFrom(final CopyFrom obj) {
        final BitloveModule mod = (BitloveModule) obj;
        this.setGuid(mod.getGuid());
    }

    @Override
    public String getUri() {
        return BitloveModule.URI;
    }

    @Override
    public Object clone() throws CloneNotSupportedException {
        final BitloveModuleImpl mod = new BitloveModuleImpl();
        mod.setGuid(this.getGuid());
        return mod;
    }

    @Override
    public boolean equals(final Object obj) {
        return EqualsBean.beanEquals(BitloveModuleImpl.class, this, obj);
    }

    @Override
    public int hashCode() {
        return EqualsBean.beanHashCode(this);
    }

    @Override
    public String toString() {
        return ToStringBean.toString(BitloveModuleImpl.class, this);
    }

}
