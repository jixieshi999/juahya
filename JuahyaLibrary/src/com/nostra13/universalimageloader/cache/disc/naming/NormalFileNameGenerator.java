/*******************************************************************************
 * Copyright 2011-2013 Sergey Tarasevich
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 *******************************************************************************/
package com.nostra13.universalimageloader.cache.disc.naming;

/**
 * Names image file as image URI {@linkplain String#hashCode() hashcode}
 * 
 * @author jixieshi 20130410
 * @since 1.3.1
 */
public class NormalFileNameGenerator implements FileNameGenerator {
	@Override
	public String generate(String imageUri) {
//		return String.valueOf(imageUri.hashCode());

	    String bitmapName = String.valueOf(imageUri.hashCode());
        bitmapName=bitmapName+".jpg";
//	    String bitmapName = imageUri
//                .substring(imageUri.lastIndexOf("/") + 1);
//        if(bitmapName.endsWith(".jpg")||bitmapName.endsWith(".jpeg")||bitmapName.endsWith(".png")){
//            
//        }else{
//            bitmapName=bitmapName+".jpg";
//        }
		return bitmapName;
	}
}
