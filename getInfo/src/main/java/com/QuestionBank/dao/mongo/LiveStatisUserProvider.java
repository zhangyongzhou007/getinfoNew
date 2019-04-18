package com.QuestionBank.dao.mongo;

import com.QuestionBank.entity.LiveStatisUserEntity;
import com.QuestionBank.utils.StringUtils;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoCursor;
import com.mongodb.client.model.IndexOptions;
import com.star.database.mongo.WebAbstractProvider;
import org.bson.Document;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.text.ParseException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 单直播访客量统计
 * @ClassName: LiveStatisUserProvider 
 * @Description: 
 * @author jiangchunlin
 * @date 2018-3-2 下午4:42:28
 */
public class LiveStatisUserProvider extends WebAbstractProvider {
	
	private Logger logger = LoggerFactory.getLogger(LiveStatisUserProvider.class);
	
	private static final String tableName = "t_live_statis_visit_user";
	
	private static LiveStatisUserProvider instance = new LiveStatisUserProvider();
	
	private MongoCollection<Document> mongoCollection;
	
	public LiveStatisUserProvider() {
		super();
		mongoCollection = this.getMongoDB().getCollection(tableName);
	}
	
	public static LiveStatisUserProvider getInstance() {
		return instance;
	}
	
	@Override
	protected String getCollectionName()
	{
		return tableName;
	}

	@Override
	protected Map<Document, IndexOptions> getIndex()
	{
		Map<Document, IndexOptions> index = new HashMap<Document, IndexOptions>();
		index.put(new Document(LiveStatisUserEntity.SUBJECT_ID, 1), new IndexOptions());
		index.put(new Document(LiveStatisUserEntity.PROFILE_ID, 1), new IndexOptions());
		index.put(new Document(LiveStatisUserEntity.CURR_DATE, 1), new IndexOptions());
		return index;
	}
	


	/**
	 * @Author ZhangFuGui
	 * @Description
	 * @Date 上午 10:08 2018/11/26 0026
	 * @Param
	 * @return
	 */
	public List<String> getUserList(String subjectId) throws ParseException {


		Document filter=new Document();
		if (!StringUtils.isBlank(subjectId)){
			filter.append(LiveStatisUserEntity.SUBJECT_ID,subjectId);
		}
		logger.info("subjectId:"+subjectId);
		List<String>  stringList=new ArrayList<>();
		MongoCursor<Document> iterator = mongoCollection.find(filter).iterator();
		while (iterator.hasNext()){
			Document next = iterator.next();
			String string = next.getString(LiveStatisUserEntity.PROFILE_ID);

			if (StringUtils.isBlank(string)){
				continue;
			}
			stringList.add(next.getString(LiveStatisUserEntity.PROFILE_ID));
			logger.info("UserEntity:"+next);

		}
		System.out.println(stringList+":stringList");
		return  stringList;

	}
}
