import matplotlib.pyplot as plt
import matplotlib.image as mpimg

def show_image_with_title(title, image_path):
    # Đọc ảnh từ tệp
    img = mpimg.imread(image_path)
    
    # Tạo figure
    plt.figure(figsize=(8, 6))
    plt.imshow(img)
    plt.axis('off')  # Ẩn trục tọa độ
    
    # Thêm tiêu đề
    plt.title(title, fontsize=14, fontweight='bold')
    
    # Hiển thị ảnh
    plt.show()

# Gọi hàm với tên bài và đường dẫn ảnh
show_image_with_title("Ex4_AddSubMulDiv_Onclick", "D:\NTU\JAVA_TBDD\64131786_AndroidProgramming\Pic\Ex4_AddSubMulDiv_Onclick.png")

