package com.colab.iiqdaintellij.actions;

import com.intellij.openapi.actionSystem.ActionGroup;
import com.intellij.openapi.actionSystem.AnAction;
import com.intellij.openapi.actionSystem.AnActionEvent;
import com.colab.iiqdaintellij.utils.IIQPlugin;
import groovyjarjarantlr4.v4.runtime.misc.NotNull;

import java.util.List;

public class DeployArtifactActionGroup extends ActionGroup {

    @NotNull
    @Override
    public AnAction[] getChildren(AnActionEvent event) {
        List<String> targetEnvironments = IIQPlugin.getTargetEnvironments(event.getProject());

        AnAction[] actionsArray = new AnAction[targetEnvironments.size()];
        for (int i = 0; i < targetEnvironments.size(); i++) {
            String str = targetEnvironments.get(i);
            DeployAction obj = new DeployAction(str);
            actionsArray[i] = obj;
        }
        return actionsArray;
    }

    ;
}