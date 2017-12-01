package com.twosigma.documentation.java.extensions;

import com.twosigma.documentation.java.parser.JavaCode;
import com.twosigma.documentation.java.parser.JavaMethod;
import com.twosigma.documentation.java.parser.JavaMethodParam;
import com.twosigma.documentation.java.parser.JavaMethodReturn;
import com.twosigma.documentation.parser.MarkupParserResult;
import com.twosigma.documentation.parser.docelement.DocElement;
import com.twosigma.documentation.template.TextTemplate;
import com.twosigma.utils.CollectionUtils;
import com.twosigma.utils.ResourceUtils;

import java.util.List;
import java.util.Map;

import static java.util.stream.Collectors.joining;
import static java.util.stream.Collectors.toList;

/**
 * @author mykola
 */
public class JavaDocParamsIncludePlugin extends JavaIncludePluginBase {
    @Override
    public String id() {
        return "java-doc-params";
    }

    @Override
    public JavaIncludeResult process(JavaCode javaCode) {
        JavaMethod javaMethod = javaCode.findMethod(entry);
        List<Map<Object, Object>> params = javaMethod.getParams().stream().map(p -> CollectionUtils.createMap("name", p.getName(),
                "description", p.getJavaDocText(),
                "type", p.getType())).collect(toList());

        TextTemplate textTemplate = new TextTemplate("java-doc-params",
                ResourceUtils.textContent("templates/javaDocParams.md"));

        MarkupParserResult parserResult = componentsRegistry.parser().parse(markupPath,
                textTemplate.process(
                        CollectionUtils.createMap("params", params,
                                "return", createReturn(javaMethod))));

        return new JavaIncludeResult(parserResult.getDocElement().getContent(), extractText(javaMethod.getParams()));
    }

    private String extractText(List<JavaMethodParam> params) {
        return params.stream().map(p -> p.getName() + " " + p.getType() + " " + p.getJavaDocText())
                .collect(joining(" "));
    }

    private Map<String, ?> createReturn(JavaMethod javaMethod) {
        JavaMethodReturn methodReturn = javaMethod.getJavaMethodReturn();
        if (methodReturn == null) {
            return null;
        }

        return CollectionUtils.createMap("type", methodReturn.getType(), "description", methodReturn.getJavaDocText());
    }
}
