package org.example.ast;

import org.codehaus.groovy.ast.ASTNode;
import org.codehaus.groovy.ast.MethodNode
import org.codehaus.groovy.ast.expr.MethodCallExpression
import org.codehaus.groovy.ast.expr.VariableExpression
import org.codehaus.groovy.ast.stmt.BlockStatement
import org.codehaus.groovy.ast.stmt.ExpressionStatement
import org.codehaus.groovy.ast.stmt.Statement;
import org.codehaus.groovy.control.CompilePhase;
import org.codehaus.groovy.control.SourceUnit;
import org.codehaus.groovy.transform.ASTTransformation;
import org.codehaus.groovy.transform.GroovyASTTransformation;

@GroovyASTTransformation(phase = CompilePhase.SEMANTIC_ANALYSIS)
public class LoggableAstTransformation implements ASTTransformation {
    @Override
    public void visit(ASTNode[] nodes, SourceUnit source) {
        MethodNode method = nodes[1] as MethodNode
        def existedStatments = ((BlockStatement)method.code).statements
        existedStatments.eachWithIndex{ def entry, int i ->
            if (entry.getText().contains('log')) {
                existedStatments.set(i, createUpperLogCodeFagment())
            }
        }
    }

    private static Statement createUpperLogCodeFagment() {
        def upperLogVarExpr = new VariableExpression("LOG")
        new ExpressionStatement(upperLogVarExpr)
    }
}
