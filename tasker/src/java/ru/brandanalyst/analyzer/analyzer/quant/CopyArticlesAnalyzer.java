package ru.brandanalyst.analyzer.analyzer.quant;

import ru.brandanalyst.core.db.provider.ProvidersHandler;
import ru.brandanalyst.core.db.provider.interfaces.ArticleProvider;
import ru.brandanalyst.core.model.Article;
import ru.brandanalyst.core.util.Batch;

import java.util.List;

/**
 * @author daddy-bear
 *         Date: 03.03.12
 */
public class CopyArticlesAnalyzer extends StubAnalyzer {
    Batch<Article> copyBatch;

    @Override
    public void init(ProvidersHandler pureProvedrsHandler) {
        final ArticleProvider articleProvider = pureProvedrsHandler.getArticleProvider();
        copyBatch = new Batch<Article>() {
            @Override
            public void handle(List<Article> articles) {
                articleProvider.writeListOfArticlesToDataStore(articles);
            }
        };
    }

    @Override
    public void analyze(Article article) {
        copyBatch.submit(article);
    }

    @Override
    public void flush() {
        copyBatch.flush();
    }
}
