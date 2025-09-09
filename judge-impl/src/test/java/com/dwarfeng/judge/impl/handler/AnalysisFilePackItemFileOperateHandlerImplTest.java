package com.dwarfeng.judge.impl.handler;

import com.dwarfeng.dutil.basic.io.IOUtil;
import com.dwarfeng.ftp.handler.FtpHandler;
import com.dwarfeng.judge.stack.bean.dto.AnalysisFilePackItemFile;
import com.dwarfeng.judge.stack.bean.dto.AnalysisFilePackItemFileDownloadInfo;
import com.dwarfeng.judge.stack.bean.dto.AnalysisFilePackItemFileUploadInfo;
import com.dwarfeng.judge.stack.bean.entity.AnalysisFilePack;
import com.dwarfeng.judge.stack.handler.AnalysisFilePackItemFileOperateHandler;
import com.dwarfeng.judge.stack.service.AnalysisFilePackItemInfoMaintainService;
import com.dwarfeng.judge.stack.service.AnalysisFilePackMaintainService;
import com.dwarfeng.subgrade.stack.bean.key.LongIdKey;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.io.ByteArrayOutputStream;
import java.io.InputStream;

import static org.junit.Assert.assertArrayEquals;
import static org.junit.Assert.assertFalse;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = "classpath:spring/application-context*.xml")
public class AnalysisFilePackItemFileOperateHandlerImplTest {

    @Autowired
    private ApplicationContext ctx;
    @Autowired
    private AnalysisFilePackItemFileOperateHandler analysisFilePackItemFileOperateHandler;
    @Autowired
    private AnalysisFilePackMaintainService analysisFilePackMaintainService;
    @Autowired
    private AnalysisFilePackItemInfoMaintainService analysisFilePackItemInfoMaintainService;

    @Autowired
    private FtpHandler ftpHandler;

    @Autowired
    private FtpPathResolver ftpPathResolver;

    private AnalysisFilePack analysisFilePack;

    @Before
    public void setUp() {
        analysisFilePack = new AnalysisFilePack(null, 0, "remark");
    }

    @After
    public void tearDown() {
        analysisFilePack = null;
    }

    @Test
    public void test() throws Exception {
        try {
            analysisFilePack.setKey(analysisFilePackMaintainService.insertOrUpdate(analysisFilePack));

            // 拿到 test-avatar.png，获取字节数组。
            byte[] content;
            try (
                    InputStream in = ctx.getResource("classpath:ftp/test-avatar.png").getInputStream();
                    ByteArrayOutputStream out = new ByteArrayOutputStream()
            ) {
                IOUtil.trans(in, out, 4096);
                out.flush();
                content = out.toByteArray();
            }
            AnalysisFilePackItemFileUploadInfo analysisFilePackItemFileUploadInfo =
                    new AnalysisFilePackItemFileUploadInfo(analysisFilePack.getKey(), "test-avatar.png", content);

            LongIdKey longIdKey = analysisFilePackItemFileOperateHandler.uploadFile(
                    analysisFilePackItemFileUploadInfo
            );
            // 下载文件，文件的内容应该与 content 相等。
            AnalysisFilePackItemFile analysisFilePackFile =
                    analysisFilePackItemFileOperateHandler.downloadFile(
                            new AnalysisFilePackItemFileDownloadInfo(longIdKey)
                    );
            assertArrayEquals(content, analysisFilePackFile.getContent());

            analysisFilePackItemInfoMaintainService.delete(longIdKey);
            assertFalse(ftpHandler.existsFile(
                    ftpPathResolver.resolvePath(FtpPathResolver.RELATIVE_ANALYSIS_FILE_PACK_ITEM_FILE),
                    Long.toString(longIdKey.getLongId())
            ));
        } finally {
            analysisFilePackMaintainService.deleteIfExists(analysisFilePack.getKey());
        }
    }
}
