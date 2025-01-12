package com.ruiyu.setting

import com.intellij.ui.components.CheckBox
import com.intellij.ui.components.JBLabel
import com.intellij.ui.components.JBScrollPane
import com.intellij.ui.components.JBTextField
import com.intellij.ui.table.JBTable
import com.intellij.util.ui.JBDimension
import wu.seal.jsontokotlin.utils.addComponentIntoVerticalBoxAlignmentLeft
import java.awt.BorderLayout
import javax.swing.*
import javax.swing.border.EmptyBorder


class SettingLayout(settingState: Settings) {
    private val panel: JPanel = JPanel(BorderLayout())
    private val checkBox: JCheckBox
    private val beanNameTextField: JBTextField
    private val ignoreContainFieldClassTextField: JBTextField
    private val configTableModel = ConfigTableModel(settingState)

    init {

        val beanNameLayout = createLinearLayoutVertical()
        checkBox = JCheckBox("whether to add a class prefix")
        checkBox.border = EmptyBorder(5, 0, 5, 0)
        checkBox.isSelected = settingState.addPrefix
        beanNameLayout.addComponentIntoVerticalBoxAlignmentLeft(checkBox)
        val beanName = JBLabel()
        beanName.border = EmptyBorder(5, 0, 5, 0)
        beanName.text = "model suffix"
        beanNameLayout.addComponentIntoVerticalBoxAlignmentLeft(beanName)
        beanNameTextField = JBTextField(settingState.modelSuffix)
        beanNameTextField.preferredSize = JBDimension(400, 40)
        beanNameLayout.addComponentIntoVerticalBoxAlignmentLeft(beanNameTextField)

        val ignoreContainFieldClassTextFieldName = JBLabel()
        ignoreContainFieldClassTextFieldName.border = EmptyBorder(5, 0, 5, 0)
        ignoreContainFieldClassTextFieldName.text = "ignoreContainFieldClass"
        beanNameLayout.addComponentIntoVerticalBoxAlignmentLeft(ignoreContainFieldClassTextFieldName)
        ignoreContainFieldClassTextField = JBTextField(settingState.ignoreContainFieldClass)
        ignoreContainFieldClassTextField.preferredSize = JBDimension(400, 40)
        beanNameLayout.addComponentIntoVerticalBoxAlignmentLeft(ignoreContainFieldClassTextField)

        panel.add(beanNameLayout, BorderLayout.NORTH)

        val label1 = JBLabel()
        label1.border = EmptyBorder(5, 0, 5, 0)
        label1.text = "Configure scan suffix files(Please separate them with commas)"
        beanNameLayout.addComponentIntoVerticalBoxAlignmentLeft(
            label1
        )


        val jbTable = JBTable(configTableModel)
        jbTable.rowSelectionAllowed = true
        jbTable.setSelectionMode(ListSelectionModel.SINGLE_SELECTION)
        val jScrollPane = JBScrollPane(jbTable)
        beanNameLayout.addComponentIntoVerticalBoxAlignmentLeft(
            jScrollPane
        )
        panel.add(createLinearLayoutVertical(), BorderLayout.AFTER_LAST_LINE)
    }

    fun getRootComponent(): JComponent {
        return this.panel
    }

    fun getSuffixFiles(): List<Array<String>> {
        return configTableModel.data
    }

    fun getModelSuffix(): String {
        return beanNameTextField.text
    }

    fun getModelPrefix(): Boolean {
        return checkBox.isSelected
    }


    fun getIgnoreContainFieldClassTextField(): String {
        return ignoreContainFieldClassTextField.text
    }
}

fun createLinearLayoutVertical(): JPanel {
    val container = JPanel()
    val boxLayout = BoxLayout(container, BoxLayout.PAGE_AXIS)
    container.layout = boxLayout
    return container
}