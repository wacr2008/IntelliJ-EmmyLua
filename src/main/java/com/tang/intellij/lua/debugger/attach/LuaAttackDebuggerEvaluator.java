/*
 * Copyright (c) 2017. tangzx(love.tangzx@qq.com)
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

package com.tang.intellij.lua.debugger.attach;

import com.intellij.xdebugger.XSourcePosition;
import com.intellij.xdebugger.evaluation.XDebuggerEvaluator;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

/**
 *
 * Created by tangzx on 2017/4/2.
 */
public class LuaAttackDebuggerEvaluator extends XDebuggerEvaluator {
    private LuaAttachDebugProcess process;

    LuaAttackDebuggerEvaluator(LuaAttachDebugProcess process) {
        this.process = process;
    }

    @Override
    public void evaluate(@NotNull String express, @NotNull XEvaluationCallback xEvaluationCallback, @Nullable XSourcePosition xSourcePosition) {
        process.getBridge().eval(express, result -> {
            if (result.isSuccess()) {
                xEvaluationCallback.evaluated(result.getXValue());
            } else {
                xEvaluationCallback.errorOccurred("error");
            }
        });
    }
}
