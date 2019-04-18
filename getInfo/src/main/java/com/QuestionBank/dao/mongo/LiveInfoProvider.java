package com.QuestionBank.dao.mongo;

import com.QuestionBank.entity.LiveInfoEntity;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoCursor;
import com.mongodb.client.model.IndexOptions;
import com.star.database.mongo.WebAbstractProvider;
import org.bson.Document;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

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
public class LiveInfoProvider extends WebAbstractProvider {

	private Logger logger = LoggerFactory.getLogger(LiveInfoProvider.class);

	private static final String tableName = "t_subject_new";

	private static LiveInfoProvider instance = new LiveInfoProvider();

	private MongoCollection<Document> mongoCollection;

	public LiveInfoProvider() {
		super();
		mongoCollection = this.getMongoDB().getCollection(tableName);
	}
	
	public static LiveInfoProvider getInstance() {
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
		index.put(new Document(LiveInfoEntity.SUBJECT_ID, 1), new IndexOptions());
		return index;
	}



	public List<LiveInfoEntity> getLiveInfoList(String startTime, String endTime)  {

		Document filter=new Document();

		filter.append(LiveInfoEntity.CREATE_TIME,new Document("$gte",startTime+" 00:00:00").append("$lte",endTime+" 23:59:59"));
		System.out.println(filter+":filter");

		logger.info("filter:"+filter);
		List<String>  stringList=new ArrayList<>();
		MongoCursor<Document> iterator = mongoCollection.find(filter).iterator();
		List<LiveInfoEntity> liveInfoEntities=new ArrayList<>();
		while (iterator.hasNext()){
			LiveInfoEntity liveInfoEntity=new LiveInfoEntity() ;
			Document next = iterator.next();
			liveInfoEntity.setSubjectId(next.getString(LiveInfoEntity.SUBJECT_ID));
			liveInfoEntity.setTitle(next.getString(LiveInfoEntity.TITLE));
			liveInfoEntity.setSchoolName(next.getString(LiveInfoEntity.SCHOOL_NAME));
			liveInfoEntity.setCreateTime(next.getString(LiveInfoEntity.CREATE_TIME));
			LiveInfoEntity liveInfo = LiveStatisProvider.getInstance().getLiveInfoList(liveInfoEntity);
			liveInfoEntities.add(liveInfo);
			logger.info("UserEntity:"+next);
		}
		System.out.println(stringList+":stringList");
		return  liveInfoEntities;
	}
}
