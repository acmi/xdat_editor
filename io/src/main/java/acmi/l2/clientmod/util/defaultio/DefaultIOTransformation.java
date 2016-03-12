/*
 * Copyright (c) 2016 acmi
 *
 * Permission is hereby granted, free of charge, to any person obtaining a copy
 * of this software and associated documentation files (the "Software"), to deal
 * in the Software without restriction, including without limitation the rights
 * to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
 * copies of the Software, and to permit persons to whom the Software is
 * furnished to do so, subject to the following conditions:
 *
 * The above copyright notice and this permission notice shall be included in
 * all copies or substantial portions of the Software.
 *
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
 * IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
 * FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
 * AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
 * LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
 * OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN
 * THE SOFTWARE.
 */
package acmi.l2.clientmod.util.defaultio;

import acmi.l2.clientmod.util.IOEntity;
import acmi.l2.clientmod.util.IOUtil;
import acmi.l2.clientmod.util.UIEntity;
import javafx.scene.paint.Color;
import org.codehaus.groovy.ast.*;
import org.codehaus.groovy.ast.expr.ArgumentListExpression;
import org.codehaus.groovy.ast.expr.Expression;
import org.codehaus.groovy.ast.expr.MethodCallExpression;
import org.codehaus.groovy.ast.expr.VariableExpression;
import org.codehaus.groovy.ast.stmt.BlockStatement;
import org.codehaus.groovy.control.CompilePhase;
import org.codehaus.groovy.control.SourceUnit;
import org.codehaus.groovy.transform.AbstractASTTransformation;
import org.codehaus.groovy.transform.GroovyASTTransformation;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.List;

import static org.codehaus.groovy.ast.ClassHelper.make;
import static org.codehaus.groovy.ast.tools.GeneralUtils.*;

@GroovyASTTransformation(phase = CompilePhase.SEMANTIC_ANALYSIS)
public class DefaultIOTransformation extends AbstractASTTransformation {
    private static final Class MY_CLASS = DefaultIO.class;
    private static final ClassNode MY_TYPE = make(MY_CLASS);
    private static final String MY_TYPE_NAME = "@" + MY_TYPE.getNameWithoutPackage();

    private static final ClassNode IOUTIL_TYPE = make(IOUtil.class);
    private static final ClassNode STRING_TYPE = make(String.class);
    private static final ClassNode COLOR_TYPE = make(Color.class);
    private static final ClassNode RGBA_TYPE = make(RGBA.class);
    private static final ClassNode LIST_TYPE = make(List.class);
    private static final ClassNode LENGTH_TYPE = make(Length.class);
    private static final ClassNode IOENTITY_TYPE = make(IOEntity.class);
    private static final ClassNode UIENTITY_TYPE = make(UIEntity.class);
    private static final ClassNode INPUTSTREAM_TYPE = make(InputStream.class);
    private static final ClassNode OUTPUTSTREAM_TYPE = make(OutputStream.class);

    @Override
    public void visit(ASTNode[] nodes, SourceUnit source) {
        init(nodes, source);
        AnnotatedNode parent = (AnnotatedNode) nodes[1];
        AnnotationNode anno = (AnnotationNode) nodes[0];
        if (!MY_TYPE.equals(anno.getClassNode())) return;

        if (parent instanceof ClassNode) {
            ClassNode cNode = (ClassNode) parent;
            if (!checkNotInterface(cNode, MY_TYPE_NAME)) return;

            List<FieldNode> fNodes = getInstancePropertyFields(cNode);

            createReadMethod(cNode, fNodes);
            createWriteMethod(cNode, fNodes);
        }
    }

    private void createReadMethod(ClassNode cNode, List<FieldNode> fNodes) {
        if (hasDeclaredMethod(cNode, "read", 1)) return;

        final BlockStatement body = new BlockStatement();
        Parameter input = param(INPUTSTREAM_TYPE, "input");

        if (superImplementsInterface(cNode, IOENTITY_TYPE))
            body.addStatement(stmt(callSuperX("read", varX(input))));

        for (FieldNode fNode : fNodes) {
            String suffix = suffixForField(fNode);
            Expression args;
            switch (suffix) {
                case "List":
                    //TODO @Length
                    args = args(varX(input), classX(fNode.getType().getGenericsTypes()[0].getType()));
                    break;
                case "UIEntity":
                    args = args(varX(input),
                            new MethodCallExpression(new MethodCallExpression(new MethodCallExpression(VariableExpression.THIS_EXPRESSION, "getClass", new ArgumentListExpression()), "getPackage", new ArgumentListExpression()), "getName", new ArgumentListExpression()),
                            new MethodCallExpression(new MethodCallExpression(VariableExpression.THIS_EXPRESSION, "getClass", new ArgumentListExpression()), "getClassLoader", new ArgumentListExpression()));
                    break;
                case "IOEntity":
                case "Enum":
                    args = args(varX(input), classX(fNode.getType()));
                    break;
                default:
                    args = args(varX(input));
            }
            body.addStatement(assignS(varX(fNode), callX(IOUTIL_TYPE, "read" + suffix, args)));
        }

        body.addStatement(returnS(VariableExpression.THIS_EXPRESSION));

        ClassNode[] exceptions = {make(IOException.class)};
        cNode.addMethod(new MethodNode(
                "read",
                ACC_PUBLIC,
                cNode.getPlainNodeReference(),
                params(input),
                exceptions,
                body));
    }

    private void createWriteMethod(ClassNode cNode, List<FieldNode> fNodes) {
        if (hasDeclaredMethod(cNode, "write", 1)) return;

        final BlockStatement body = new BlockStatement();
        Parameter output = param(OUTPUTSTREAM_TYPE, "output");

        if (superImplementsInterface(cNode, IOENTITY_TYPE))
            body.addStatement(stmt(callSuperX("write", varX(output))));

        fNodes.forEach(fNode -> body.addStatement(stmt(callX(IOUTIL_TYPE, "write" + suffixForField(fNode), args(varX(output), varX(fNode))))));

        body.addStatement(returnS(VariableExpression.THIS_EXPRESSION));

        ClassNode[] exceptions = {make(IOException.class)};
        cNode.addMethod(new MethodNode(
                "write",
                ACC_PUBLIC,
                cNode.getPlainNodeReference(),
                params(output),
                exceptions,
                body));
    }

    private String suffixForField(FieldNode fNode) {
        if (fNode.getType().equals(ClassHelper.int_TYPE)) return "Int";
        if (fNode.getType().equals(ClassHelper.Boolean_TYPE) || fNode.getType().equals(ClassHelper.boolean_TYPE))
            return "Boolean";
        if (fNode.getType().equals(ClassHelper.float_TYPE)) return "Float";
        if (fNode.getType().isEnum()) return "Enum";
        if (fNode.getType().equals(STRING_TYPE)) return "String";
        if (fNode.getType().equals(COLOR_TYPE)) {
            if (fNode.getAnnotations(RGBA_TYPE).size() > 0)
                return "RGBA";
            return "Color";
        }
        if (fNode.getType().equals(ClassHelper.LIST_TYPE.getPlainNodeReference())) return "List";
        if (fNode.getType().declaresInterface(UIENTITY_TYPE)) return "UIEntity";
        if (fNode.getType().declaresInterface(IOENTITY_TYPE)) return "IOEntity";
        throw new IllegalStateException("Unknown field type: " + fNode.getType());
    }

    private boolean superImplementsInterface(ClassNode cNode, ClassNode interfaceClassNode) {
        for (ClassNode node = cNode.redirect().getSuperClass(); node != null; node = node.getSuperClass()) {
            if (node.declaresInterface(interfaceClassNode)) {
                return true;
            }
        }
        return false;
    }
}
