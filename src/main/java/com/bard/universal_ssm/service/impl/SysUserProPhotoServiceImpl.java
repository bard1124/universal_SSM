package com.bard.universal_ssm.service.impl;

import com.bard.universal_ssm.dao.SysUserProPhotoDao;
import com.bard.universal_ssm.framework.constants.Constant;
import com.bard.universal_ssm.framework.constants.Message;
import com.bard.universal_ssm.framework.exception.BusinessException;
import com.bard.universal_ssm.framework.utils.SummaryUtils;
import com.bard.universal_ssm.model.bo.LoginUserBo;
import com.bard.universal_ssm.model.po.SysUserProPhotoPo;
import com.bard.universal_ssm.service.SysUserProPhotoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.*;
import java.util.List;

@Service
public class SysUserProPhotoServiceImpl implements SysUserProPhotoService {
	
	@Autowired
	private SysUserProPhotoDao sysUserProPhotoDao;

	public List<SysUserProPhotoPo> getProfilePhoto(Integer userId) {
		List<SysUserProPhotoPo> sysUserProPhotoPoList = sysUserProPhotoDao.selectProPhotoByUserId(userId);
		for(SysUserProPhotoPo sysUserProPhotoPo: sysUserProPhotoPoList)
			sysUserProPhotoPo.setImageUrl(Constant.STATIC_FILE_URL + sysUserProPhotoPo.getImageUrl());
		return sysUserProPhotoPoList;
	}

	public void insertProfilePhoto(MultipartFile img, LoginUserBo loginUserBo) throws BusinessException {
		BufferedInputStream bis = null;
		//将之前的图像删除
		//这边文件并没有删除，留底
		sysUserProPhotoDao.deleteByUserId(loginUserBo.getId());
		try {
			//BufferedInputStream可以将流复用
			bis = new BufferedInputStream(img.getInputStream());
			//设置mark设置为最大
			bis.mark(Integer.MAX_VALUE);
			//重置流
			bis.reset();
			//将图片压缩成30x30
			saveImage(bis, 1, loginUserBo);
			//重置流
			bis.reset();
			//将图片压缩成50x50
			saveImage(bis, 2, loginUserBo);
			//重置流
			bis.reset();
			//将图片压缩成180x180
			saveImage(bis, 3, loginUserBo);
		} catch (IOException e) {
			throw new BusinessException(Message.PRO_PHOTO_OPEN_IMAGE);
		} finally {
			if(bis != null)
				try {
					bis.close();
				} catch (IOException e) {
				}
		}
	}
	
	/**
	 * 保存图片
	 * @param inputStream
	 * @param size:1=30x30, 2=50x50, 3=180x180
	 * @param user
	 * @throws BusinessException
	 */
	private void saveImage(InputStream inputStream, int size, LoginUserBo loginUserBo) throws BusinessException {
		OutputStream os = null;
		ByteArrayOutputStream byteArrayOutputStream = null;
		byte[] outImageData;
		try {
			int width;
			int height;
			//创建一个byte流用于接受压缩后文件的数据
			byteArrayOutputStream = new ByteArrayOutputStream();
			//读取源图像
			BufferedImage bufferedImage = ImageIO.read(inputStream);
			switch(size) {
			case 1:
				width = 30;
				height = 30;
				break;
			case 2:
				width = 50;
				height = 50;
				break;
			case 3:
				width = 180;
				height = 180;
				break;
			default:
				return;
			}
			//获取压缩过后的image对象
			Image image = bufferedImage.getScaledInstance(width, height, Image.SCALE_SMOOTH);
			BufferedImage tagBufferedImage = new BufferedImage(width, height, BufferedImage.TYPE_INT_RGB);
			Graphics g = tagBufferedImage.getGraphics();
			g.drawImage(image, 0, 0, null);
			g.dispose();
			//将缩放过后的图片流写入到byte流里去
			ImageIO.write(tagBufferedImage, "JPEG", byteArrayOutputStream);
			//将byte流转换成Byte数组
			outImageData = byteArrayOutputStream.toByteArray();
		} catch (IOException e) {
			throw new BusinessException(Message.PRO_PHOTO_OPEN_IMAGE);
		} finally {
			if(byteArrayOutputStream != null)
				try {
					byteArrayOutputStream.close();
				} catch (IOException e) {
				}
		}
		try {
			//根据压缩过后的图像数据计算md5值作为文件名
			//这样可以保证不会存在重复的文件
			//同时相同的文件只可能存在一份
			String fileName = SummaryUtils.md5sum(outImageData) + ".jpg";
			//获取存放用户头像的文件夹
			File dir = new File(Constant.STATIC_FILE_PATH + Constant.STATIC_FILE_TPYE_IMAGE, Constant.STATIC_FILE_PRO_PHOTO_PATH);
			//如果文件夹不存在则创建
			if(!dir.exists())
				dir.mkdirs();
			//将压缩过后的图像数据写入到文件中
			os = new FileOutputStream(new File(dir, fileName));
			os.write(outImageData);
			os.flush();
			//将头像数据插入到数据库
			SysUserProPhotoPo sysUserProPhotoPo = new SysUserProPhotoPo();
			sysUserProPhotoPo.setUserid(loginUserBo.getId());
			sysUserProPhotoPo.setSize(size);
			sysUserProPhotoPo.setImageUrl(Constant.STATIC_FILE_TPYE_IMAGE + "/" + Constant.STATIC_FILE_PRO_PHOTO_PATH + "/" + fileName);
			sysUserProPhotoPo.setCreateUser(loginUserBo.getUserName());
			sysUserProPhotoDao.insert(sysUserProPhotoPo);
		} catch (IOException e) {
			throw new BusinessException(Message.FILEUTILS_CANT_WRITE_FILE);
		} finally {
			if(os != null)
				try {
					os.close();
				} catch (IOException e) {
				}
		}
	}
}
