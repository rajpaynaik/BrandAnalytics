package ru.brandanalyst.core.util;

import org.apache.log4j.Logger;
import de.l3s.boilerpipe.BoilerpipeExtractor;
import de.l3s.boilerpipe.BoilerpipeProcessingException;
import de.l3s.boilerpipe.extractors.ArticleExtractor;

/**
 * Alexandra Mikhaylova mikhaylova@yandex-team.ru
 */
public class ArticleCleaner {
    private static final Logger log = Logger.getLogger(ArticleCleaner.class);

    public static String cleanArticle(final String article) {
        final BoilerpipeExtractor extractor = new ArticleExtractor();
        try {
            return extractor.getText(article);
        } catch (BoilerpipeProcessingException e) {
            log.info("Error: " + e.toString() + "! Article has not been cleaned!");
            return article;
        }
    }
}