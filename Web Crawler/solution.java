public class Web_Crawler {

    class Solution_dfs {

        Set<String> res = new HashSet<>(); // 返回结果

        public List<String> crawl(String startUrl, HtmlParser htmlParser) {
            String host = getUrlHost(startUrl); // 取得域名
            res.add(startUrl); // 将startUrl添加至返回结果
            dfs(startUrl, host, htmlParser); // 开始dfs
            return new ArrayList<>(res);
        }

        void dfs(String startUrl, String host, HtmlParser htmlParser) {
            // 取得当前页面包含的所有链接
            List<String> urls = htmlParser.getUrls(startUrl);
            // 通过每一个链接继续dfs
            for (String url : urls) {
                // 如果该链接已经爬过或是与网站域名不一致时跳过
                if (res.contains(url) || !getUrlHost(url).equals(host)) {
                    continue;
                }
                // 将该链接加入返回结果
                res.add(url);
                // 继续dfs
                dfs(url, host, htmlParser);
            }
        }

        private String getUrlHost(String url) {
            String[] args = url.split("/");
            return args[2];
        }
    }


    class Solution_bfs {
        Set<String> res = new HashSet<>(); // 返回结果

        public List<String> crawl(String startUrl, HtmlParser htmlParser) {
            String host = getUrlHost(startUrl); // 取得域名
            Queue<String> q = new LinkedList<>(); // bfs用的queue
            res.add(startUrl); // 添加startUrl至返回结果
            q.offer(startUrl); // 添加startUrl至bfs的Queue
            while (q.size() > 0) {
                String url = q.poll(); // 取得一个url
                // 查看当前url包含的所有链接
                List<String> urls = htmlParser.getUrls(url);
                for (String u : urls) { // 循环每一个链接
                    // 如果该链接已经爬过或者不属于当前域名，跳过
                    if (res.contains(u) || !getUrlHost(u).equals(host)) {
                        continue;
                    }
                    res.add(u); // 添加该链接至返回结果
                    q.offer(u); // 添加该链接至bfs的Queue
                }
            }
            return new ArrayList<>(res);
        }

        private String getUrlHost(String url) {
            String[] args = url.split("/");
            return args[2];
        }
    }

    interface HtmlParser {
        // Return a list of all urls from a webpage of given url.
        // This is a blocking call, that means it will do HTTP request and return when this request is finished.
        List<String> getUrls(String str);
    }
}

############

/**
 * // This is the HtmlParser's API interface.
 * // You should not implement it, or speculate about its implementation
 * interface HtmlParser {
 *     public List<String> getUrls(String url) {}
 * }
 */

class Solution {
    private Set<String> ans;

    public List<String> crawl(String startUrl, HtmlParser htmlParser) {
        ans = new HashSet<>();
        dfs(startUrl, htmlParser);
        return new ArrayList<>(ans);
    }

    private void dfs(String url, HtmlParser htmlParser) {
        if (ans.contains(url)) {
            return;
        }
        ans.add(url);
        for (String next : htmlParser.getUrls(url)) {
            if (host(next).equals(host(url))) {
                dfs(next, htmlParser);
            }
        }
    }

    private String host(String url) {
        url = url.substring(7);
        return url.split("/")[0];
    }
}
