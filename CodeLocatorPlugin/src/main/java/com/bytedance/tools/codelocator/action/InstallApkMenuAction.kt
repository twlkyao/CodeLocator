package com.bytedance.tools.codelocator.action

import com.bytedance.tools.codelocator.utils.FileUtils
import com.bytedance.tools.codelocator.utils.Log
import com.bytedance.tools.codelocator.utils.Mob
import com.intellij.openapi.actionSystem.AnAction
import com.intellij.openapi.actionSystem.AnActionEvent
import com.intellij.openapi.actionSystem.PlatformDataKeys
import com.intellij.openapi.actionSystem.Presentation
import com.intellij.openapi.project.Project
import java.io.File

class InstallApkMenuAction : AnAction() {

    lateinit var project: Project

    override fun actionPerformed(e: AnActionEvent) {
        project = e.project!!
        FileUtils.init(project)
        var virtualFile = e.getData(PlatformDataKeys.VIRTUAL_FILE)
        val presentableUrl = virtualFile!!.getPresentableUrl()
        Log.d("安装 $presentableUrl")
        Mob.mob(Mob.Action.CLICK, Mob.Button.INSTALL_APK_RIGHT)
        InstallApkAction.installApkFile(project, File(presentableUrl))
    }

    override fun update(e: AnActionEvent) {
        var virtualFile = e.getData(PlatformDataKeys.VIRTUAL_FILE)
        val presentation: Presentation = e.getPresentation()
        presentation.isVisible = (virtualFile != null && !(virtualFile!!.isDirectory || !virtualFile!!.name.endsWith("apk")))
    }
}