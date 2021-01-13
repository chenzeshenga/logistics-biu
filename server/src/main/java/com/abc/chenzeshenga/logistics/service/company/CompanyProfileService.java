package com.abc.chenzeshenga.logistics.service.company;

import com.abc.chenzeshenga.logistics.mapper.CompanyProfileMapper;
import com.abc.chenzeshenga.logistics.model.CompanyProfile;
import com.abc.chenzeshenga.logistics.model.common.PageData;
import com.abc.chenzeshenga.logistics.model.common.PageQueryEntity;
import com.abc.chenzeshenga.logistics.model.common.Pagination;
import com.abc.chenzeshenga.logistics.model.common.SqlLimit;
import com.abc.chenzeshenga.logistics.util.SqlUtils;
import java.util.List;
import javax.annotation.Resource;
import org.springframework.stereotype.Service;

/**
 * @author chenzeshenga
 * @since 2020-12-21
 */
@Service
public class CompanyProfileService {

  @Resource private CompanyProfileMapper companyProfileMapper;

  public CompanyProfile init(String userId) {
    return companyProfileMapper.init(userId);
  }

  public PageData<CompanyProfile> listProfile(PageQueryEntity<String> userIdPageQueryEntity) {
    String userId = userIdPageQueryEntity.getEntity();
    Pagination pagination = userIdPageQueryEntity.getPagination();
    SqlLimit sqlLimit = SqlUtils.generateSqlLimit(pagination);
    List<CompanyProfile> companyProfileList = companyProfileMapper.list(userId, sqlLimit);
    long total = companyProfileMapper.count(userId);
    PageData<CompanyProfile> pageData = new PageData<>();
    pageData.setData(companyProfileList);
    pageData.setTotal(total);
    return pageData;
  }
}
