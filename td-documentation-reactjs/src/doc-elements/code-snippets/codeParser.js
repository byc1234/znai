import Prism from 'prismjs'
import 'prismjs/components/prism-java'
import 'prismjs/components/prism-clike'
import 'prismjs/components/prism-javascript'
import 'prismjs/components/prism-python'
import 'prismjs/components/prism-c'
import 'prismjs/components/prism-markdown'
import 'prismjs/components/prism-cpp'
import 'prismjs/components/prism-json'

export {parseCode}

function parseCode(lang, code) {
    const prismLang = Prism.languages[adjustLang(lang)]

    const tokens = Prism.tokenize(code, prismLang ? prismLang : Prism.languages.clike)
    return tokens.map(normalizeToken)
}

function adjustLang(lang) {
    if (lang === 'csv') {
        return 'clike'
    }

    if (lang === 'js') {
        return 'javascript'
    }

    return lang
}

function normalizeToken(token) {
    if (typeof token === 'string') {
        return {type: "text", data: token}
    }

    return {type: token.type, data: normalizeData(token.content)}
}

function normalizeData(data) {
    if (typeof data === 'string') {
        return data
    }

    if (Array.isArray(data)) {
        return data.map(d => normalizeData(d)).join("")
    }

    if (typeof data === 'object') {
        return data.content
    }

    return JSON.stringify(data)
}