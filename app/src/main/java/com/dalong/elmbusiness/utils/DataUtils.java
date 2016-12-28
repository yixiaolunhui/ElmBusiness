package com.dalong.elmbusiness.utils;

import com.google.gson.Gson;

import java.util.Arrays;
import java.util.List;


/**
 * Created by zhouweilong on 2016/12/27.
 */

public class DataUtils {

    public static  String data="{\n" +
            "    \"data\": [\n" +
            "        {\n" +
            "            \"goodscatrgory\": {\n" +
            "                \"goodsitem\": [\n" +
            "                    {\n" +
            "                        \"name\": \"苹果\",\n" +
            "                        \"price\": 10,\n" +
            "                        \"introduce\": \"苹果好吃啊，很甜！\",\n" +
            "                        \"goodsImgUrl\": \"https://ss1.bdstatic.com/70cFvXSh_Q1YnxGkpoWK1HF6hhy/it/u=1701109266,1081476103&fm=116&gp=0.jpg\",\n" +
            "                        \"moreStandard\": false\n" +
            "                    },\n" +
            "                    {\n" +
            "                        \"name\": \"香蕉\",\n" +
            "                        \"price\": 10,\n" +
            "                        \"introduce\": \"香蕉好吃啊，又大很甜！\",\n" +
            "                        \"goodsImgUrl\": \"https://ss0.bdstatic.com/70cFvHSh_Q1YnxGkpoWK1HF6hhy/it/u=4150969577,207675143&fm=116&gp=0.jpg\",\n" +
            "                        \"moreStandard\": false\n" +
            "                    },\n" +
            "                    {\n" +
            "                        \"name\": \"橘子\",\n" +
            "                        \"price\": 10,\n" +
            "                        \"introduce\": \"橘子非常好吃啊，很甜！\",\n" +
            "                        \"goodsImgUrl\": \"https://ss0.bdstatic.com/70cFvHSh_Q1YnxGkpoWK1HF6hhy/it/u=244020795,2296627467&fm=116&gp=0.jpg\",\n" +
            "                        \"moreStandard\": false\n" +
            "                    },\n" +
            "                    {\n" +
            "                        \"name\": \"榴莲\",\n" +
            "                        \"price\": 10,\n" +
            "                        \"introduce\": \"我对象喜欢吃榴莲！\",\n" +
            "                        \"goodsImgUrl\": \"https://ss0.bdstatic.com/70cFvHSh_Q1YnxGkpoWK1HF6hhy/it/u=1241578183,1527853673&fm=116&gp=0.jpg\",\n" +
            "                        \"moreStandard\": false\n" +
            "                    },\n" +
            "                    {\n" +
            "                        \"name\": \"桃子\",\n" +
            "                        \"price\": 10,\n" +
            "                        \"introduce\": \"大龙家的桃子就是好吃！\",\n" +
            "                        \"goodsImgUrl\": \"https://ss1.bdstatic.com/70cFuXSh_Q1YnxGkpoWK1HF6hhy/it/u=2983725700,2237376083&fm=116&gp=0.jpg\",\n" +
            "                        \"moreStandard\": false\n" +
            "                    },\n" +
            "                    {\n" +
            "                        \"name\": \"橙子\",\n" +
            "                        \"price\": 10,\n" +
            "                        \"introduce\": \"橙子非常好吃啊，很甜！\",\n" +
            "                        \"goodsImgUrl\": \"https://ss0.bdstatic.com/70cFvHSh_Q1YnxGkpoWK1HF6hhy/it/u=2549573239,2448800271&fm=111&gp=0.jpg\",\n" +
            "                        \"moreStandard\": false\n" +
            "                    },\n" +
            "                    {\n" +
            "                        \"name\": \"梨\",\n" +
            "                        \"price\": 10,\n" +
            "                        \"introduce\": \"梨非常好吃啊，很甜！\",\n" +
            "                        \"goodsImgUrl\": \"https://ss1.bdstatic.com/70cFvXSh_Q1YnxGkpoWK1HF6hhy/it/u=1966863908,2578898835&fm=116&gp=0.jpg\",\n" +
            "                        \"moreStandard\": false\n" +
            "                    }\n" +
            "                ],\n" +
            "                \"name\": \"水果\"\n" +
            "            }\n" +
            "        },\n" +
            "        {\n" +
            "            \"goodscatrgory\": {\n" +
            "                \"goodsitem\": [\n" +
            "                    {\n" +
            "                        \"name\": \"菠菜\",\n" +
            "                        \"price\": 12,\n" +
            "                        \"introduce\": \"菠菜有营养蛮好吃的\",\n" +
            "                        \"goodsImgUrl\": \"https://ss0.bdstatic.com/70cFvHSh_Q1YnxGkpoWK1HF6hhy/it/u=1090875894,2830536606&fm=116&gp=0.jpg\",\n" +
            "                        \"moreStandard\": false\n" +
            "                    },\n" +
            "                    {\n" +
            "                        \"name\": \"韭菜\",\n" +
            "                        \"price\": 10,\n" +
            "                        \"introduce\": \"韭菜好吃还能壮阳！\",\n" +
            "                        \"goodsImgUrl\": \"https://ss1.bdstatic.com/70cFuXSh_Q1YnxGkpoWK1HF6hhy/it/u=3257122934,2954156923&fm=116&gp=0.jpg\",\n" +
            "                        \"moreStandard\": false\n" +
            "                    },\n" +
            "                    {\n" +
            "                        \"name\": \"生菜\",\n" +
            "                        \"price\": 10,\n" +
            "                        \"introduce\": \"卷饼必需品！\",\n" +
            "                        \"goodsImgUrl\": \"https://ss1.bdstatic.com/70cFuXSh_Q1YnxGkpoWK1HF6hhy/it/u=3706196441,2863505692&fm=116&gp=0.jpg\",\n" +
            "                        \"moreStandard\": false\n" +
            "                    },\n" +
            "                    {\n" +
            "                        \"name\": \"空心菜\",\n" +
            "                        \"price\": 10,\n" +
            "                        \"introduce\": \"多放点辣椒也蛮好吃的！\",\n" +
            "                        \"goodsImgUrl\": \"https://ss2.bdstatic.com/70cFvnSh_Q1YnxGkpoWK1HF6hhy/it/u=1884106436,247345386&fm=116&gp=0.jpg\",\n" +
            "                        \"moreStandard\": false\n" +
            "                    },\n" +
            "                    {\n" +
            "                        \"name\": \"芹菜\",\n" +
            "                        \"price\": 10,\n" +
            "                        \"introduce\": \"芹菜好吃降血压！\",\n" +
            "                        \"goodsImgUrl\": \"https://ss1.bdstatic.com/70cFuXSh_Q1YnxGkpoWK1HF6hhy/it/u=3881529425,3695854192&fm=116&gp=0.jpg\",\n" +
            "                        \"moreStandard\": false\n" +
            "                    },\n" +
            "                    {\n" +
            "                        \"name\": \"白菜\",\n" +
            "                        \"price\": 10,\n" +
            "                        \"introduce\": \"白菜炖粉条好吃\",\n" +
            "                        \"goodsImgUrl\": \"https://ss0.bdstatic.com/70cFuHSh_Q1YnxGkpoWK1HF6hhy/it/u=2051825384,1743284996&fm=116&gp=0.jpg\",\n" +
            "                        \"moreStandard\": false\n" +
            "                    }\n" +
            "                ],\n" +
            "                \"name\": \"蔬菜\"\n" +
            "            }\n" +
            "        }\n" +
            "    ]\n" +
            "}";

    /**
     * 转成list
     *
     * @param gsonString
     * @param cls
     * @return
     */
    public static <T> List<T> GsonToList(String gsonString, Class<T[]> cls) {
        T[] arr = new Gson().fromJson(gsonString, cls);
        return Arrays.asList(arr); //or return Arrays.asList(new Gson().fromJson(s, clazz)); for a one-liner
    }
    /**
     * 转成bean
     *
     * @param gsonString
     * @param cls
     * @return
     */
    public static <T> T GsonToBean(String gsonString, Class<T> cls) {
        T t = new Gson().fromJson(gsonString, cls);
        return t;
    }

}
