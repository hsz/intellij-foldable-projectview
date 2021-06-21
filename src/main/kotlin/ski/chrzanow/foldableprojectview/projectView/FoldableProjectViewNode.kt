package ski.chrzanow.foldableprojectview.projectView

import com.intellij.icons.AllIcons.General.CollapseComponent
import com.intellij.ide.projectView.PresentationData
import com.intellij.ide.projectView.ProjectViewNode
import com.intellij.ide.projectView.ViewSettings
import com.intellij.ide.projectView.impl.nodes.PsiFileNode
import com.intellij.openapi.project.Project
import com.intellij.openapi.vfs.VirtualFile
import com.intellij.ui.SimpleTextAttributes
import ski.chrzanow.foldableprojectview.FoldableProjectViewBundle

class FoldableProjectViewNode(
    project: Project,
    settings: ViewSettings?,
    private val children: List<PsiFileNode>,
) : ProjectViewNode<String>(project, FoldableProjectViewBundle.message("foldableProjectView.name"), settings) {

    override fun update(presentation: PresentationData) {
        presentation.apply {
            val text = FoldableProjectViewBundle.message("foldableProjectView.node", children.size)
            val toolTip = children.mapNotNull { it.name }.joinToString(", ")
            val textAttributes = SimpleTextAttributes.GRAY_SMALL_ATTRIBUTES
            addText(ColoredFragment(text, toolTip, textAttributes))
            setIcon(CollapseComponent)
        }
    }

    override fun getChildren() = children

    override fun contains(file: VirtualFile) = children.firstOrNull { it.virtualFile == file } != null
}